package catoNetworks;

public class Leetcode_79_WordSearch {

    static void main() {
        var wordSearch = new Leetcode_79_WordSearch();
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        var word = "ABCB";

        IO.println(
                wordSearch.exist(board, word)
        );
    }

    public static class Trie {
        private static final int LIMIT = 256;
        private final Trie[] nodes;
        private int count;
        private int end;

        public Trie() {
            this.nodes = new Trie[LIMIT];
        }

        public Trie getNode(char ch) {
            return this.nodes[ch];
        }

        public void insert(String word) {
            var current = this;
            for (char ch : word.toCharArray()) {
                if (current.nodes[ch] == null) {
                    current.nodes[ch] = new Trie();
                }
                current = current.nodes[ch];
                current.count++;
            }
            current.end++;
        }

        public boolean contains(char ch) {
            return this.nodes[ch] != null;
        }
    }

    private static final int[] DIRECTION = {0, 1, 0, -1, 0};

    public boolean exist(char[][] board, String word) {
        var trie = new Trie();
        trie.insert(word);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (trie.contains(board[i][j]) && checkWord(board, trie.getNode(board[i][j]), i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkWord(char[][] board, Trie trie, int x, int y) {
        if (trie.end > 0) return true;
        char current = board[x][y];
        board[x][y] = '#';
        for (int d = 0; d < DIRECTION.length - 1; d++) {
            int newX = x + DIRECTION[d];
            int newY = y + DIRECTION[d + 1];
            if (newX >= 0 && newX < board.length
                    && newY >= 0 && newY < board[newX].length
                    && trie.contains(board[newX][newY]) && checkWord(board, trie.getNode(board[newX][newY]), newX, newY)) {
                return true;
            }
        }
        board[x][y] = current;
        return false;
    }
}
