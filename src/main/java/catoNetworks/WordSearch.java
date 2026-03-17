package catoNetworks;

// Time complexity O(n * m)
// Space complexity O(n * m)
public class WordSearch {
    static void main() {
        var wordSearch = new WordSearch();
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        var word = "SEEK";

        IO.println(
                wordSearch.exist(board, word)
        );
    }

    private static final int[] DIRECTION = {0, 1, 0, -1, 0};

    public boolean exist(char[][] board, String word) {
        var visited = new boolean[board.length][board[0].length];
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (checkWord(board, visited, word, x, y, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkWord(char[][] board, boolean[][] visited, String word, int x, int y, int i) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[x].length)
            return false;
        if (visited[x][y] || word.charAt(i) != board[x][y])
            return false;
        if(i == word.length() - 1)
            return true;

        visited[x][y] = true;
        for(int d = 0; d < DIRECTION.length - 1; d++) {
            int newX = x + DIRECTION[d];
            int newY = y + DIRECTION[d + 1];
            if(checkWord(board, visited, word, newX, newY, i + 1)) {
                visited[x][y] = false;
                return true;
            }
        }
        visited[x][y] = false;
        return false;
    }


}
