package karat.riddler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NonoGram {
    static void main() {

        char[][] matrix1 = {
                {'W','W','W','W'},
                {'B','W','W','W'},
                {'B','W','B','B'},
                {'W','W','B','W'},
                {'B','B','W','W'}};

        int[][] rows1_1 = {{},{1},{1,2},{1},{2}};
        int[][] columns1_1 = {{2,1},{1},{2},{1}};

        IO.println(validateNonogram(matrix1, rows1_1, columns1_1));

        int[][] rows1_2 = {{},{},{1},{1},{1,1}};
        int[][] columns1_2 = {{2},{1},{2},{1}};

        IO.println(validateNonogram(matrix1, rows1_2, columns1_2));

        int[][] rows1_3 = {{},{1},{3},{1},{2}};
        int[][] columns1_3 = {{3},{1},{2},{1}};

        IO.println(validateNonogram(matrix1, rows1_3, columns1_3));

        int[][] rows1_4 = {{},{1,1},{1,2},{1},{2}};
        int[][] columns1_4 = {{2,1},{1},{2},{1}};

        IO.println(validateNonogram(matrix1, rows1_4, columns1_4));

        int[][] rows1_5 = {{},{1},{1},{1},{2}};
        int[][] columns1_5 = {{2,1},{1},{2},{1}};

        IO.println(validateNonogram(matrix1, rows1_5, columns1_5));

        int[][] rows1_6 = {{},{1},{2,1},{1},{2}};
        int[][] columns1_6 = {{2,1},{1},{2},{1}};

        IO.println(validateNonogram(matrix1, rows1_6, columns1_6));

        int[][] rows1_7 = {{},{1},{1,2},{1},{2,1}};
        int[][] columns1_7 = {{2,1},{1},{2},{1}};

        IO.println(validateNonogram(matrix1, rows1_7, columns1_7));

        int[][] rows1_8 = {{1},{1},{1,2},{1},{2}};
        int[][] columns1_8 = {{2,1},{1},{2},{1}};

        IO.println(validateNonogram(matrix1, rows1_8, columns1_8));

        char[][] matrix2 = {
                {'W','W'},
                {'B','B'},
                {'B','B'},
                {'W','B'}};

        int[][] rows2_1 = {{},{2},{2},{1}};
        int[][] columns2_1 = {{1,1},{3}};

        IO.println(validateNonogram(matrix2, rows2_1, columns2_1));

        int[][] rows2_2 = {{},{2},{2},{1}};
        int[][] columns2_2 = {{3},{3}};

        IO.println(validateNonogram(matrix2, rows2_2, columns2_2));

        int[][] rows2_3 = {{},{},{},{}};
        int[][] columns2_3 = {{},{}};

        IO.println(validateNonogram(matrix2, rows2_3, columns2_3));

        int[][] rows2_4 = {{},{2},{2},{1}};
        int[][] columns2_4 = {{2,1},{3}};

        IO.println(validateNonogram(matrix2, rows2_4, columns2_4));

        int[][] rows2_5 = {{},{2},{2},{1}};
        int[][] columns2_5 = {{2},{3}};

        IO.println(validateNonogram(matrix2, rows2_5, columns2_5));

        int[][] rows2_6 = {{},{2},{2},{1}};
        int[][] columns2_6 = {{2},{1,1}};

        IO.println(validateNonogram(matrix2, rows2_6, columns2_6));

        char[][] matrix3 = {
                {'B','W','B','B','W','B'}};

        int[][] rows3_1 = {{1,2,1}};
        int[][] columns3_1 = {{1},{},{1},{1},{},{1}};

        IO.println(validateNonogram(matrix3, rows3_1, columns3_1));

        int[][] rows3_2 = {{1,2,2}};
        int[][] columns3_2 = {{1},{},{1},{1},{},{1}};

        IO.println(validateNonogram(matrix3, rows3_2, columns3_2));
    }

    // Let:
    // r = number of rows.
    // c = number of columns
    // Time Complexity: O(r × c)
    // Space Complexity: O(max(r, c))
    public static boolean validateNonogram(
            char[][] matrix,
            int[][] rows,
            int[][] columns) {
        int r = matrix.length;
        int c = matrix[0].length;

        for (int i = 0; i < r; i++) {
            List<Integer> actual = getRuns(matrix[i]);
            if (!actual.equals(toList(rows[i]))) {
                return false;
            }
        }

        for (int j = 0; j < c; j++) {
            char[] column = new char[r];
            for (int i = 0; i < r; i++) {
                column[i] = matrix[i][j];

            }
            List<Integer> actual = getRuns(column);
            if (!actual.equals(toList(columns[j]))) {
                return false;
            }
        }
        return true;

    }

    private static List<Integer> getRuns(char[] line) {
        List<Integer> runs = new ArrayList<>();
        int count = 0;
        for (char cell : line) {
            if (cell == 'B') {
                count++;
            } else {
                if (count > 0) {
                    runs.add(count);
                    count = 0;
                }
            }
        }
        if (count > 0) {
            runs.add(count);
        }
        return runs;
    }

    private static List<Integer> toList(int[] instruction) {
        return IntStream.of(instruction).boxed().toList();
    }
}
