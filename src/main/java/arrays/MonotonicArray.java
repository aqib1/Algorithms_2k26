package arrays;

public class MonotonicArray {
    static void main() {
        var mono = new MonotonicArray();
        System.out.println(
                mono.check(
                        new int[]{6, 5, 4, 2, 1}
                )
        );
    }

    // Time complexity O(n) and space is O(1)
    public boolean check(int[] arr) {
        if (arr == null || arr.length == 0)
            return false;
        var increasing = false;
        var decreasing = false;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i])
                increasing = true;
            if (arr[i - 1] > arr[i])
                decreasing = true;

            if (increasing && decreasing)
                return false;
        }

        return true;
    }
}
