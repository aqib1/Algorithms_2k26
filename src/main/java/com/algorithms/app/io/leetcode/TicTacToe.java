package com.algorithms.app.io.leetcode;

public class TicTacToe {
    static void main() {
        var tic = new TicTacToe();
        System.out.println(
                tic.ticTacToe(
                        new int[][] {
                                {0, 0},
                                {1, 1},
                                {2, 0},
                                {1, 0},
                                {1, 2},
                                {2, 1},
                                {0, 1},
                                {0, 2},
                                {2, 2}
                        }
                )
        );
    }

    // Time complexity O(n) and Space O(3 x 3)
    public String ticTacToe(int[][] moves) {
        var sign = new char[3][3];
        for(int i = 0; i < moves.length; i++) {
            if(i % 2 == 0) {
                sign[moves[i][0]][moves[i][1]] = 'X';
            } else {
                sign[moves[i][0]][moves[i][1]] = 'O';
            }
        }
        return isWinner(sign, 'X') ? "A" : isWinner(sign, 'O') ? "B" : isPending(sign) ? "Pending" : "Draw";
    }

    private boolean isPending(char[][] sign) {
        for (char[] chars : sign) {
            for (char aChar : chars) {
                if (aChar == '\u0000')
                    return true;
            }
        }
        return false;
    }

    private boolean isWinner(char[][] sign, char x) {
        for(int i = 0; i < 3; i++) {
            if(sign[0][i] == x && sign[1][i] == x && sign[2][i] == x)
                return true;
            if(sign[i][0] == x && sign[i][1] == x && sign[i][2] == x)
                return true;
        }
        if(sign[0][0] == x && sign[1][1] == x && sign[2][2] == x)
            return true;
        return sign[0][2] == x && sign[1][1] == x && sign[2][0] == x;
    }


}
