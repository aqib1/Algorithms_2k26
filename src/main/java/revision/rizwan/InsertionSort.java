package revision.rizwan;

import java.util.Arrays;
import java.util.Objects;

public class InsertionSort {

    // Time complexities
    // O(1) - constant
    // O(logn) - logerthmic
    // O(n) - linear
    // O(nlogn) - lineararthmic
    // O(n2) - quardratic
    // O(2^n) - exponantial
    // O(n!) - factorial


    // i = 1
    // key = [i]
    // j = i - 1
    // while(j >= 0 && ??)
    // j--;
    // [j + 1] = [j]
    // [j + 1] = key

    // start kro 1 = i;
    // key = arr[i];
    // j = i - 1;
    // j >=0 && ??
    // [j + 1] = [j];
    // j--;
    // loop sy bahar = [j+1] = key
    private void sort(int []arr) {
        if(Objects.isNull(arr) || arr.length == 0)
            return;

        for(int i = 1; i < arr.length; i++) {
            int key = arr[i]; // 2
            int j = i - 1; // 3
            while(j >= 0 && key < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    static void main() {
        var data = new int[] {3, 8, 2, 9, 4, 1};
        var s = new InsertionSort();
        s.sort(data);
        IO.println(Arrays.toString(data));
    }
}
