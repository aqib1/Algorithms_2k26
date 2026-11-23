package arrays;

public class MonotonicArray {
    static void main() {
        var mono = new MonotonicArray();
        System.out.println(
                mono.check(
                        new int[] {6, 5, 4, 2 , 1}
                )
        );
    }

    // Time complexity O(n) and space is O(1)
    public boolean check(int [] arr) {
        if(arr == null || arr.length == 0)
            return false;
        if(arr.length == 1)
            return true;

        // base
        var monotonic = arr[0] <= arr[1] ? 0 : 1;

        for(int i = 1; i < arr.length - 1; i++) {
            if(monotonic == 0 && arr[i] > arr[i + 1])
                return false;
            if(monotonic == 1 && arr[i] < arr[i + 1])
                return false;
        }

        return true;
    }
}
