package arrays;

import java.util.Arrays;
import java.util.Objects;

// Time complexity O(nLogn)
// Space O(n)
public class MergeSort {
    private int[] arr;
    private int[] tempArr;
    public void sort(int[] arr) {
        if(Objects.isNull(arr) || arr.length == 0)
            return;
        this.arr = arr;
        this.tempArr = new int[arr.length];
        mergeSort(0, arr.length - 1);
    }

    private void mergeSort(int start, int end) {
        if(start >= end)
            return;
        int mid = start + (end - start) / 2;
        mergeSort(start, mid);
        mergeSort(mid + 1, end);
        
        merge(start, mid, end);
    }

    private void merge(int start, int mid, int end) {
        for(int i = start; i <= end; i++) {
            tempArr[i] = arr[i];
        }

        int k = start;
        int i = start;
        int j = mid + 1;

        while(i <= mid && j <= end) {
            if(tempArr[i] <= tempArr[j]) {
                arr[k] = tempArr[i++];
            } else {
                arr[k] = tempArr[j++];
            }
            k++;
        }

        while(i <= mid) {
            arr[k++] = tempArr[i++];
        }

        while(j <= end) {
            arr[k++] = tempArr[j++];
        }
    }

    static void main() {
        var data = new int[] {4, 3, 1, 22 ,3, 0};
        var mergeSort = new MergeSort();
        mergeSort.sort(data);
        IO.println(Arrays.toString(data));
    }
}
