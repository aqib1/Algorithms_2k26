package com.algorithms.app.io.leetcode;

public class AddBinary {

    static void main() {
        System.out.println(addBinary("11", "1"));
    }

    public String addBinarySlow(String a, String b) {
        var n1 = Integer.parseInt(a);
        var n2 = Integer.parseInt(b);
        return Integer.toBinaryString(n1 + n2);
    }

    public static String addBinary(String a, String b) {
        var builder = new StringBuilder();

        var carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;

        while(i >= 0 || j >= 0 || carry > 0) {
            if(i >= 0) {
                carry += a.charAt(i--) - '0';
            }
            if(j >= 0) {
                carry += b.charAt(j--) - '0';
            }
            builder.append(carry % 2);
            carry /= 2;
        }

        return builder.reverse().toString();
    }
}
