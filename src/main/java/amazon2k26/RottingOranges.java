package amazon2k26;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Time complexity = O(N x M)
 * Space complexity = O(N x M)
 * where N is number of rows and M is number of columns.
 * */
public class RottingOranges {
    static void main() {
        var rottingOranges = new RottingOranges();
        System.out.println(
                rottingOranges.orangesRotting(
                        new int[][]{
                                {2, 1, 1},
                                {1, 1, 0},
                                {0, 1, 1}
                        }
                )
        );
    }

    private static final int[] DIRECTIONS = {0, 1, 0, -1, 0};

    public int orangesRotting(int[][] grid) {
        int minutes = 0;
        int freshOranges = 0;
        Queue<int[]> fifo = new LinkedList<>();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == 2) {
                    fifo.add(new int[]{x, y});
                } else if(grid[x][y] == 1) {
                    freshOranges++;
                }
            }
        }

        if(freshOranges == 0) return 0;

        while (!fifo.isEmpty()) {
            var size = fifo.size();
            for (int i = 0; i < size; i++) {
                var dir = fifo.poll();
                if (dir != null) {
                    for (int d = 0; d < DIRECTIONS.length - 1; d++) {
                        int newX = dir[0] + DIRECTIONS[d];
                        int newY = dir[1] + DIRECTIONS[d + 1];
                        if (newX >= 0 && newX < grid.length
                                && newY >= 0 && newY < grid[newX].length
                                && grid[newX][newY] == 1) {
                            grid[newX][newY] = 2;
                            fifo.add(new int[]{newX, newY});
                            freshOranges--;
                        }
                    }
                }
            }
            minutes++;
        }
        return freshOranges == 0 ? minutes - 1 : -1;
    }


}
