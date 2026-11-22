package revision.rizwan;


import java.util.Arrays;
import java.util.Objects;

// O(nlogn) - O(n)
public class MergeSort {

    static void main() {
        var data = new int[]{4, 5, 1, 2, 3, 11, 2};
        var sort = new MergeSort();
        sort.sort(data);
        IO.println(Arrays.toString(data));
    }

    private int[] arr;
    private int[] tmpArr;
    private void sort(int[] arr) {
        if(Objects.isNull(arr) || arr.length == 0)
            return;

        this.arr = arr;
        this.tmpArr = new int[arr.length];
        mergeSort(0, arr.length - 1);
    }

    private void mergeSort(int start, int end) {
        if(start >= end)
            return;
        int mid = start + (end - start) / 2;
        mergeSort(start, mid);
        mergeSort(mid + 1 , end);

        merge(start, mid, end);
    }

    private void merge(int start, int mid, int end) {
        for(int i = start; i <= end; i++)
            tmpArr[i] = arr[i];

        int k = start;
        int i = start;
        int j = mid + 1;
        while(i <= mid && j <= end) {
            if(tmpArr[i] <= tmpArr[j]) {
                arr[k] = tmpArr[i++];
            } else {
                arr[k] = tmpArr[j++];
            }
            k++;
        }

        while(i <= mid) {
            arr[k++] = tmpArr[i++];
        }

        while(j <= end) {
            arr[k++] = tmpArr[j++];
        }
    }
}
