package com.algorithms.app.io.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AverageSalary {

    // Time complexity O(n)
    // Space complexity O(1)
    public double averageFast(int[] salary) {
        var min = salary[0];
        var max = salary[0];
        var sum = 0;

        for(var s: salary) {
            sum += s;
            min = Math.min(min, s);
            max = Math.max(max, s);
        }

        return (double)(sum - max - min) / (salary.length - 2);
    }

    // slow and with additional space
    // Time complexity O(n), space O(n)
    public double average(int[] salary) {
        var minHeap = new PriorityQueue<int[]>(
                Comparator.comparingInt(a -> a[1])
        );

        var maxHeap = new PriorityQueue<int[]>(
                (a, b) -> Integer.compare(b[1], a[1])
        );

        for(int i = 0; i < salary.length; i++) {
            minHeap.offer(new int[]{i, salary[i]});
            maxHeap.offer(new int[] {i, salary[i]});
        }

        var min = minHeap.poll();
        var max = maxHeap.poll();
        var sum = 0;
        for(int i = 0; i < salary.length; i++) {
            if(i != min[0] && i != max[0]) {
                sum += salary[i];
            }
        }
        return (double) sum / (salary.length - 2);
    }
}
