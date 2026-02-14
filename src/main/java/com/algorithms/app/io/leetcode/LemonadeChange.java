package com.algorithms.app.io.leetcode;

public class LemonadeChange {
    static void main() {
        System.out.println(
                lemonadeChange(new int[] {5,5,10,10,20})
        );
    }

    // Time complexity O(n) and space O(1)
    public static boolean lemonadeChange(int[] bills) {
        var fives = 0; var tens = 0;
        for(var bill: bills) {
            if(bill == 5) {
                fives++;
            } else if(bill == 10) {
                if(fives >= 1) {
                    fives--;
                    tens++;
                } else {
                    return false;
                }
            } else {
                if(fives >= 1 && tens >= 1) {
                    fives--;
                    tens--;
                } else if(fives >= 3) {
                    fives -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
