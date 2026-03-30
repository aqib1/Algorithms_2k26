package ocp.test;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    static void main() {
        System.out.println(generate(5));
    }
    public static List<List<Integer>> generate(int numRows) {
        if(numRows < 1) {
            return new ArrayList<>();
        }

        List<List<Integer>> pascal = new ArrayList<>();
        pascal.add(List.of(1));

        for(int i = 1; i < numRows; i++) {
            var data = new ArrayList<Integer>();
            data.add(1);

            for(int j = 1; j < i; j++) {
                data.add(pascal.get(i - 1).get(j - 1) + pascal.get(i - 1).get(j));
            }

            data.add(1);
            pascal.add(data);
        }
        return pascal;
    }
}
