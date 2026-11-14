package karat.riddler;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubSudoku {
    static void main() {
        int[][] grid1 = {
                {2, 3, 1},
                {1, 2, 3},
                {3, 1, 2}
        };
        System.out.println(isValidGrid(grid1));


        int[][] grid2 =  {
                {2, 3, 1},
                {1, 2, 3},
                {3, 2, 1}
        };
        System.out.println(isValidGrid(grid2));
    }

    // Let: N is total numbers in grid
    // Time Complexity: O(N²)
    // Space Complexity: O(N)
    public static boolean isValidGrid(int[][] grid) {
        var expectedSet = IntStream.range(1, grid.length + 1)
                .boxed()
                .collect(Collectors.toSet());

        for(int i = 0; i < grid.length; i++) {
            var rowSet = new HashSet<Integer>();
            for(int j = 0; j < grid[i].length; j++) {
                rowSet.add(grid[i][j]);
            }

            if(!expectedSet.equals(rowSet)) {
                return false;
            }
        }

        for(int i = 0; i < grid.length; i++) {
            var colSet = new HashSet<Integer>();
            for(int j = 0; j < grid[i].length; j++) {
                colSet.add(grid[j][i]);
            }

            if(!expectedSet.equals(colSet)) {
                return false;
            }
        }

        return true;
    }
}
