package wise.hackerrank;

import java.util.List;
import java.util.PriorityQueue;

// [9, 2, 8] -> 10
// [10, 9] -> 19
// [19] -> 29
public class CostMinimization {

    static void main() {
        var cost = new CostMinimization();
        IO.println(
                cost.minimizeCost(
                        List.of(9, 2, 8)
                )
        );
    }

    public long minimizeCost(List<Integer> arr) {
        var minHeap = new PriorityQueue<Integer>();
        for(int a: arr)
            minHeap.offer(a);

        long totalMinimumCost = 0L;

        while(minHeap.size() > 1) {
            var sum = minHeap.poll() + minHeap.poll();

            totalMinimumCost += sum;
            minHeap.offer(sum);

        }

        return totalMinimumCost;
    }
}
