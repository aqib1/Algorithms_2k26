package arrays;

import java.util.Objects;

public class InsertionSort {
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
}
