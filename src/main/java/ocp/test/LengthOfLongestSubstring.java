package ocp.test;

public class LengthOfLongestSubstring {

    // Time complexity O(n) and space is O(256)
    public int longestSubstring(String str) {
        var count = new char[256];
        var charArr = str.toCharArray();
        int maxLen = 0;
        int i = 0, j = 0;
        while(j < charArr.length) {
            if(count[charArr[j]] < 1) {
                count[charArr[j++]]++;
                maxLen = Math.max(maxLen, j - i);
            } else {
                count[charArr[i++]]--;
            }
        }

        return maxLen;
    }
}
