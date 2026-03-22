package amazon2k26;

public class NumberOfIslands {

    static void main() {
        var n = new NumberOfIslands();
        IO.println(
                n.numIslands(
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
