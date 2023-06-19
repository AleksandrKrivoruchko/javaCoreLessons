package ru.avk;

import java.util.Random;

public class GameBoard {
    private final int[] boardInt = new int[9];

    private final int E = 0;
    private final int X = 1;
    private final int O = 2;

    public GameBoard() {
        fillBoard();
    }

    public int[] getBoardInt() {
        return boardInt;
    }

    public void printBoard(int[] array) {
        int count = 1;
        for (int i = 0; i < array.length; i++, count++) {
            System.out.print(convertIntToString(array[i]) + " ");
            if (count == 3) {
                count = 0;
                System.out.println();
            }
        }
    }
    private void fillBoard() {
        Random random = new Random();
        for (int i = 0; i < boardInt.length; i++) {
            int n = random.nextInt(3);
            boardInt[i] = n;
        }
    }

    private String convertIntToString(int n) {
        return switch (n) {
            case E -> "E";
            case X -> "X";
            default -> "O";
        };
    }

    public int convertBoardToInt() {
        int result = 0;
        int shift = 16;
        for (int i = boardInt.length - 1; i >= 0; i--) {
            result += boardInt[i] << shift;
            shift -= 2;
        }
        return result;
    }

    public int[] convertIntToBoard(int n) {
        int[] array = new int[9];
        int mask = 3;
        int shift = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = (n & (mask << shift)) >> shift;
            shift += 2;
        }
        return array;
    }
}
