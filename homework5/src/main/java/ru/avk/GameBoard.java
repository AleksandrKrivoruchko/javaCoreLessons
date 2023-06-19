package ru.avk;

import java.util.Random;

public class GameBoard {
    private final String[] board = new String[9];

    public GameBoard() {
        fillBoard();
    }

    public void printBoard() {
        int count = 1;
        for (int i = 0; i < board.length; i++, count++) {
            System.out.print(board[i] + " ");
            if (count == 3) {
                count = 0;
                System.out.println();
            }
        }
    }
    private void fillBoard() {
        Random random = new Random();
        for (int i = 0; i < board.length; i++) {
            int n = random.nextInt(3);
            switch (n) {
                case 0 -> board[i] = "E";
                case 1 -> board[i] = "X";
                case 2 -> board[i] = "O";
            }
        }
    }
}
