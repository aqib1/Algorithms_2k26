package com.algorithms.app.io.ripple.revision;

import java.util.*;

public class StockPrice {
    private int maxTimestamp;
    private final Map<Integer, StockRecord> stockMap;
    private final Queue<StockRecord> minHeap;
    private final Queue<StockRecord> maxHeap;
    public StockPrice() {
        maxTimestamp = Integer.MIN_VALUE;
        stockMap = new HashMap<>();
        minHeap = new PriorityQueue<>(
                Comparator.comparingInt(StockRecord::price)
        );
        maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.price, a.price));
    }

    public void update(int timestamp, int price) {
        maxTimestamp = Math.max(maxTimestamp, timestamp);
        var stockRecord = new StockRecord(timestamp, price);
        stockMap.put(timestamp, stockRecord);
        minHeap.offer(stockRecord);
        maxHeap.offer(stockRecord);
    }

    public int current() {
        return stockMap.get(maxTimestamp).price;
    }

    public int maximum() {
        if(maxHeap.isEmpty())
            return -1;

        while(!maxHeap.isEmpty() && maxHeap.peek().price != stockMap.get(maxHeap.peek().timestamp).price) {
            maxHeap.poll();
        }

        return maxHeap.isEmpty() ? -1 : maxHeap.peek().price;
    }

    public int minimum() {
        if(minHeap.isEmpty())
            return -1;

        while(!minHeap.isEmpty() && minHeap.peek().price != stockMap.get(minHeap.peek().timestamp).price) {
            minHeap.poll();
        }

        return minHeap.isEmpty() ? -1 : minHeap.peek().price;
    }


    record StockRecord(int timestamp, int price) {

    }
}
