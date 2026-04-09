package wise.hackerrank;

import java.util.List;
import java.util.PriorityQueue;

public class SignFlip {

    static void main() {
        var sign = new SignFlip();
        System.out.println(
                sign.maximizeSum(List.of(2, 3, -5), 2)
        );
    }

    public long maximizeSum(List<Integer> arr, int k) {
        var minHeap = new PriorityQueue<Integer>();
        for(int num: arr) {
            minHeap.offer(num);
        }

        while(k-- > 0 && !minHeap.isEmpty()) {
            minHeap.offer(-minHeap.poll());
        }

        long sum = 0;
        while(!minHeap.isEmpty()) {
            sum += minHeap.poll();
        }
        return sum;
    }
}
