package com.algorithms.app.io.ripple;

import java.util.*;

public class StockPrice {
    private int maxTimestamp = Integer.MIN_VALUE;
    private final Queue<StockRecord> minHeap;
    private final Queue<StockRecord> maxHeap;
    private final Map<Integer, Integer> timestampToPrice;

    // Time complexity overall ON(LogN)
    // Space complexity O(3N) = O(N)
    public StockPrice() {
        this.timestampToPrice = new HashMap<>();
        this.minHeap = new PriorityQueue<>(Comparator.comparing(StockRecord::price));
        this.maxHeap = new PriorityQueue<>(
                (a, b) ->
                        Integer.compare(b.price(), a.price())
        );
    }

    public void update(int timestamp, int price) {
        this.maxTimestamp = Math.max(maxTimestamp, timestamp);
        this.minHeap.add(new StockRecord(timestamp, price));
        this.maxHeap.add(new StockRecord(timestamp, price));
        this.timestampToPrice.put(timestamp, price);
    }

    public int current() {
        return this.timestampToPrice.get(maxTimestamp);
    }

    public int maximum() {
        if(maxHeap.isEmpty())
            return -1;

        while(maxHeap.peek().price != timestampToPrice.get(maxHeap.peek().timestamp)) {
            maxHeap.poll();
        }

        return maxHeap.peek().price;
    }

    public int minimum() {
        if(minHeap.isEmpty())
            return -1;

        while(minHeap.peek().price != timestampToPrice.get(minHeap.peek().timestamp)) {
            minHeap.poll();
        }

        return minHeap.peek().price;
    }

    record StockRecord(int timestamp, int price) {
    }
}
