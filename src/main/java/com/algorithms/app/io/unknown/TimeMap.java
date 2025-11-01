package com.algorithms.app.io.unknown;

import java.util.*;

public class TimeMap {

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
        System.out.println(timeMap.get("foo", 1));         // return "bar"
        System.out.println(timeMap.get("foo", 3));         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
        timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
        System.out.println(timeMap.get("foo", 4));         // return "bar2"
        System.out.println(timeMap.get("foo", 5));         // return "bar2"
    }

    private final Map<String, Queue<Integer>> timestampMaxHeap;
    private final Map<String, Map<Integer, String>> timeMap;
    public TimeMap() {
        this.timeMap = new HashMap<>();
        this.timestampMaxHeap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        timeMap.put(key, Map.of(timestamp, value));
        if(this.timestampMaxHeap.containsKey(key)) {
            this.timestampMaxHeap.get(key).offer(timestamp);
        } else {
            var maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
            maxHeap.offer(timestamp);
            this.timestampMaxHeap.put(key, maxHeap);
        }
    }

    public String get(String key, int timestamp) {
        if(timeMap.containsKey(key)) {
            if(timeMap.get(key).containsKey(timestamp)) {
                return timeMap.get(key).get(timestamp);
            } else if(!timestampMaxHeap.get(key).isEmpty()) {
                return timeMap.get(key).get(timestampMaxHeap.get(key).peek());
            }
        }
        return "";
    }
}
