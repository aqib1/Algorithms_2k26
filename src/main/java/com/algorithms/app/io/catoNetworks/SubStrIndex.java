package com.algorithms.app.io.catoNetworks;

public class SubStrIndex {

    static void main() {
        IO.println(subStr("hellospringboot", "spring"));
    }

    public static int subStr(String str, String ptr) {
        if(ptr.length() > str.length())
            return -1;
        var ptrCh = ptr.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ptrCh[0] &&
                    isMatch(str, ptrCh, i)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean isMatch(String str, char[] ptrCh, int i) {
        if(i + ptrCh.length > str.length())
            return false;
        int j = 0;
        for(int x = i; x < str.length() && j < ptrCh.length; x++) {
            if(ptrCh[j++] != str.charAt(x))
                return false;
        }
        return j == ptrCh.length;
    }
}
