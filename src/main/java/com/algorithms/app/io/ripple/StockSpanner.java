package com.algorithms.app.io.ripple;

import java.util.*;

public class StockSpanner {

    public static void main(String[] args) {
        var stockSpanner = new StockSpanner();

        // [[], [100], [80], [60], [70], [60], [75], [85]]
        // Output [null, 1, 1, 1, 2, 1, 4, 6]

        System.out.println(stockSpanner.next(100));
        System.out.println(stockSpanner.next(80));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(70));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(75));
        System.out.println(stockSpanner.next(85));
    }

    // Time complexity O(LogN)
    // Space O(N)
    private final Queue<int[]> minHeap;

    public StockSpanner() {
        this.minHeap = new PriorityQueue<>(Comparator.comparing(a -> a[0]));
    }

    public int next(int price) {
        int span = 1;
        while (!minHeap.isEmpty() && minHeap.peek()[0] <= price) {
            span += minHeap.poll()[1];
        }

        minHeap.offer(new int[]{price, span});
        return span;
    }
}
