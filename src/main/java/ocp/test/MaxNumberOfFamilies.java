package ocp.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxNumberOfFamilies {

    public int count(int n, int[][] reservedSeats) {
        var map = new HashMap<Integer, List<Integer>>();
        var groupCount = 0;
        var resCount = 0;
        var groupA = false;
        var groupB = false;
        var groupC = false;

        for(int[] seat: reservedSeats) {
            if(!map.containsKey(seat[0]))
                map.put(seat[0], new ArrayList<>());

            map.get(seat[0]).add(seat[1]);
        }

        for(var key: map.keySet()) {
            groupA = true;
            groupB = true;
            groupC = true;
            resCount++;
            for(var value : map.get(key)) {
                if(value == 2 || value == 3 || value == 4 || value == 5) {
                    groupA = false;
                }

                if(value == 4 || value == 5 || value == 6 || value == 7) {
                    groupB = false;
                }

                if(value == 6 || value == 7 || value == 8 || value == 9) {
                    groupC = false;
                }
            }
            if(groupA && groupC) {
                groupCount += 2;
            } else if(groupA || groupB || groupC) {
                groupCount++;
            }
        }

        groupCount += (n - resCount) * 2;
        return groupCount;
    }

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int group = 0;
        int count = 0;
        boolean groupA;
        boolean groupB;
        boolean groupC;

        for(int[] seat : reservedSeats){
            if(!map.containsKey(seat[0])){
                map.put(seat[0], new ArrayList<>());
            }

            map.get(seat[0]).add(seat[1]);
        }

        for(int k : map.keySet()){
            groupA = true;
            groupB = true;
            groupC = true;
            count++;
            for(int v : map.get(k)){
                if(v == 2 || v == 3 || v == 4 || v == 5){
                    groupA = false;
                }
                if(v == 4 || v == 5 || v == 6 || v == 7){
                    groupB = false;
                }
                if(v == 6 || v == 7 || v == 8 || v == 9){
                    groupC = false;
                }
            }
            if(groupA && groupC){
                group += 2;
            }
            else if(groupA || groupB || groupC){
                group++;
            }
        }

        group += (n - count) * 2;
        return group;
    }
}
