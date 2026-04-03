package arrays;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

public class InsertionSort {

    // Time complexity O(n2) quadratic
    // Space complexity is O(1)
    private void sort(int [] arr, BiPredicate<Integer, Integer> comparator) {
        if(Objects.isNull(arr) || arr.length == 0)
            return;

        for(int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while(j >= 0 && comparator.test(arr[j], key)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private void sortAsc(int[] arr) {
        if(Objects.isNull(arr) || arr.length == 0)
            return;
        for(int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while(j >= 0 && key < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }


    public void sortDesc(int [] arr) {
        if(Objects.isNull(arr) || arr.length == 0)
            return;

        for(int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while(j >= 0 && key > arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    static void main() {
        var data = new int[] {1, 2, 10, 4, 5};
        var sort = new InsertionSort();
       // sort.sortDesc(data);
        sort.sort(data, (a, b) -> a > b);
        IO.println(Arrays.toString(data));
    }
}
