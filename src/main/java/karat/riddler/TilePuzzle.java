package karat.riddler;

import java.util.*;

public class TilePuzzle {
    void main() {

        // Example 1: Valid solution
        String[] start1 = {"R", "_", "B", "B"};
        String[] end1 = {"B", "_", "B", "R"};

        List<List<String>> result1 = validMoves(start1, end1);

        System.out.println("Example 1: " + result1);

        // Expected: A valid sequence of moves from start1 to end1.


        // Example 2: Multiple possible valid paths
        String[] start2 = {"R", "R", "_", "_"};
        String[] end2 = {"_", "_", "R", "R"};

        List<List<String>> result2 = validMoves(start2, end2);

        System.out.println("Example 2: " + result2);

        // Expected: One valid sequence of moves (not necessarily shortest).


        // Example 3: Impossible because red cannot move left
        String[] start3 = {"R", "B", "_"};
        String[] end3 = {"B", "R", "_"};

        List<List<String>> result3 = validMoves(start3, end3);

        System.out.println("Example 3: " + result3);

        // Expected: []


        // Example 4: Black moves left
        String[] start4 = {"_", "B", "B"};
        String[] end4 = {"B", "B", "_"};

        List<List<String>> result4 = validMoves(start4, end4);

        System.out.println("Example 4: " + result4);

        // Expected: [["_", "B", "B"], ["B", "_", "B"], ["B", "B", "_"]]


        // Example 5: Different number of tiles
        String[] start5 = {"R", "R", "B"};
        String[] end5 = {"B", "R", "_"};

        List<List<String>> result5 = validMoves(start5, end5);

        System.out.println("Example 5: " + result5);

        // Expected: []


        // Example 6: Different number of tiles
        String[] start6 = {"_", "R", "_"};
        String[] end6 = {"B", "R", "_"};

        List<List<String>> result6 = validMoves(start6, end6);

        System.out.println("Example 6: " + result6);

        // Expected: []


        // Example 7: Red and black cannot move in the required directions
        String[] start7 = {"B", "_", "R"};
        String[] end7 = {"R", "_", "B"};

        List<List<String>> result7 = validMoves(start7, end7);

        System.out.println("Example 7: " + result7);

        // Expected: []


        // Example 8: Impossible configuration
        String[] start8 = {"_", "R", "R", "B"};
        String[] end8 = {"B", "R", "R", "_"};

        List<List<String>> result8 = validMoves(start8, end8);

        System.out.println("Example 8: " + result8);

        // Expected: []


        // Example 9: Simple black movement
        String[] start9 = {"R", "_", "_", "B"};
        String[] end9 = {"R", "B", "_", "_"};

        List<List<String>> result9 = validMoves(start9, end9);

        System.out.println("Example 9: " + result9);

        // Expected:
        // [
        //   [R, _, _, B],
        //   [R, _, B, _],
        //   [R, B, _, _]
        // ]


        // Example 10: Red moves right and black moves left
        String[] start10 = {"R", "_", "B"};
        String[] end10 = {"B", "_", "R"};

        List<List<String>> result10 = validMoves(start10, end10);

        System.out.println("Example 10: " + result10);

        // Expected:
        // [
        //   [R, _, B],
        //   [_, R, B],
        //   [B, R, _],
        //   [B, _, R]
        // ]
    }

    /**
     * Let n be the board length.
     * Each board configuration can potentially generate up to O(n) possible moves.
     * There are at most:
     * 3^n possible board configurations (each cell can be R, B, or empty, giving an upper bound).
     * O(n) work to generate and process moves for each configuration.
     * Time: O(n × 3ⁿ)
     * Space: O(n × 3ⁿ)
     * This is an exponential search problem.
     *
     */
    public static List<List<String>> validMoves(
            String[] start,
            String[] end
    ) {
        List<List<String>> path = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        if (!isValidInput(start, end)) {
            return path;
        }
        String startState = String.join("", start);
        String endState = String.join("", end);
        visited.add(startState);
        path.add(Arrays.asList(start.clone()));
        if (dfs(start.clone(), endState, path, visited)) {
            return path;
        }
        return new ArrayList<>();
    }

    private static boolean dfs(
            String[] current,
            String target,
            List<List<String>> path,
            Set<String> visited) {
        String currentState = String.join("", current);
        if (currentState.equals(target)) {
            return true;
        }
        List<String[]> nextMoves = getLegalMoves(current);
        for (String[] next : nextMoves) {
            String nextState = String.join("", next);
            if (visited.contains(nextState)) {
                continue;
            }
            visited.add(nextState);
            path.add(Arrays.asList(next.clone()));
            if (dfs(next, target, path, visited)) {
                return true;
            }
            path.removeLast();
        }
        return false;
    }

    private static List<String[]> getLegalMoves(String[] board) {
        List<String[]> moves = new ArrayList<>();
        int n = board.length;
        for (int i = 0; i < n; i++) {
            if (board[i].equals("R")) {
                if (i + 1 < n && board[i + 1].equals("_")) {
                    String[] next = board.clone();
                    next[i] = "_";
                    next[i + 1] = "R";
                    moves.add(next);
                }
                if (i + 2 < n
                        && board[i + 1].equals("B")
                        && board[i + 2].equals("_")) {
                    String[] next = board.clone();
                    next[i] = "_";
                    next[i + 2] = "R";
                    moves.add(next);
                }
            } else if (board[i].equals("B")) {
                if (i - 1 >= 0 && board[i - 1].equals("_")) {
                    String[] next = board.clone();
                    next[i] = "_";
                    next[i - 1] = "B";
                    moves.add(next);
                }
                if (i - 2 >= 0
                        && board[i - 1].equals("R")
                        && board[i - 2].equals("_")) {
                    String[] next = board.clone();
                    next[i] = "_";
                    next[i - 2] = "B";
                    moves.add(next);
                }
            }
        }
        return moves;
    }

    private static boolean isValidInput(
            String[] start,
            String[] end
    ) {
        if (start.length != end.length) {
            return false;
        }
        Map<String, Integer> counts = new HashMap<>();
        for (String tile : start) {
            counts.put(tile, counts.getOrDefault(tile, 0) + 1);
        }
        for (String tile : end) {
            counts.put(tile, counts.getOrDefault(tile, 0) - 1);
        }

        for (int count : counts.values()) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
