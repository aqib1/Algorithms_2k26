package arrays;

import java.util.Arrays;

public class MergeSort {
    private void sort(int[] arr) {
    }


    static void main() {
        var data = new int[] {4, 3, 1, 22 ,3, 0};
        var mergeSort = new MergeSort();
        mergeSort.sort(data);
        IO.println(Arrays.toString(data));
    }
}
