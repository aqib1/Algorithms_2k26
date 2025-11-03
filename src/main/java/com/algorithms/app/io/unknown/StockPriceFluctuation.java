package com.algorithms.app.io.unknown;

import java.util.*;

public class StockPriceFluctuation {
    static class StockPrice {
        private int maxTimestamp;
        private final Map<Integer, StockRecord> stockMap;
        private final Queue<StockRecord> minHeap;
        private final Queue<StockRecord> maxHeap;

        public StockPrice() {
            this.stockMap = new HashMap<>();
            this.minHeap = new PriorityQueue<>(Comparator.comparingInt(StockRecord::price));
            this.maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.price, a.price));
        }


        public void update(int timestamp, int price) {
            this.maxTimestamp = Math.max(timestamp, maxTimestamp);
            var stockRecord = new StockRecord(timestamp, price);
            this.stockMap.put(timestamp, stockRecord);
            this.minHeap.offer(stockRecord);
            this.maxHeap.offer(stockRecord);
        }


        public int current() {
            return stockMap.get(maxTimestamp).price();
        }

        public int maximum() {
            while(!maxHeap.isEmpty() && (maxHeap.peek().price != stockMap.get(maxHeap.peek().timestamp).price)) {
                maxHeap.poll();
            }

            return maxHeap.isEmpty() ? -1 : maxHeap.peek().price;
        }

        public int minimum() {
            while(!minHeap.isEmpty() && (minHeap.peek().price != stockMap.get(minHeap.peek().timestamp).price)) {
                minHeap.poll();
            }

            return minHeap.isEmpty() ? -1 : minHeap.peek().price;
        }

    }

    record StockRecord(int timestamp, int price) {}
}
