package ocp.test;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class RemoveDuplicates {
    static void main() {
        var i = new int[] {1, 1, 2, 3, 4, 4, 5};
        System.out.println(
                Arrays.toString(removeDuplicates(i))
        );
    }

    // Time complexity O(n)
    // Space complexity O(n)
    public static int[] removeDuplicates(int [] arr) {
        var set = new LinkedHashSet<Integer>();
        for(int a: arr)
            set.add(a);
        return set.stream().mapToInt(a -> a).toArray();
    }
}
