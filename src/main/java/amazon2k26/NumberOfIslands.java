package amazon2k26;

/**
 * Time complexity O(M x N) where M is number of rows and N is number of columns.
 * Space complexity O(M x N) in worst case when complete grid is an island.
 * */
public class NumberOfIslands {
    static void main() {
        var n = new NumberOfIslands();
        IO.println(
                n.numIslandsWithoutUsingExtraSpace(
                        new char[][]{
                                {'1', '1', '1', '1', '0'},
                                {'1', '1', '0', '1', '0'},
                                {'1', '1', '0', '0', '0'},
                                {'0', '0', '0', '0', '0'}
                        }
                )
        );
    }

    private static final int[] DIRECTION = {0, 1, 0, -1, 0};


    public int numIslandsWithoutUsingExtraSpace(char[][] grid) {
        int count = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1') {
                    markIsland(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    public void markIsland(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        for(int d = 0; d < DIRECTION.length - 1; d++) {
            int newX = i + DIRECTION[d];
            int newY = j + DIRECTION[d + 1];
            if(newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[newX].length && grid[newX][newY] == '1')
                markIsland(grid, newX, newY);
        }
    }

    public int numIslands(char[][] grid) {
        int count = 0;
        var visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    markIsland(grid, visited, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void markIsland(char[][] grid, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < DIRECTION.length - 1; i++) {
            int newX = x + DIRECTION[i];
            int newY = y + DIRECTION[i + 1];
            if (newX >= 0 && newX < grid.length
                    && newY >= 0 && newY < grid[newX].length
                    && !visited[newX][newY]
                    && grid[newX][newY] == '1') {
                markIsland(grid, visited, newX, newY);
            }
        }
    }
}
