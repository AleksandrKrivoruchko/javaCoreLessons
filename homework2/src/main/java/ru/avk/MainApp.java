package ru.avk;

import java.util.Random;
import java.util.Scanner;

public class MainApp {
    private static final int WIN_COUNT = 4;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '•';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static char[][] field;
    private static final Random random = new Random();
    private static int fieldSizeX;
    private static int fieldSizeY;

    // Ход игрока movePlayer[0] = x, movePlayer[1] = y
    private static int[] movePlayer = new int[2];


    public static void main(String[] args) {
        do {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "Вы победили!")) {
                    break;
                }
                aiTurn();
                printField();
                if (gameCheck(DOT_AI, "Компьютер победил!")) {
                    break;
                }
            }
            System.out.println("Желаете сыграть еще раз? (Y - да)");
        } while (SCANNER.next().equalsIgnoreCase("Y"));
    }

    private static void initialize() {
        fieldSizeX = 5;
        fieldSizeY = 5;
        field = new char[fieldSizeX][fieldSizeY];
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Поправленная отрисовка игрового поля
     */
    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        }
        System.out.println();

        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < fieldSizeX; j++) {
                System.out.print(field[j][i] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Обработка хода игрока (человек)
     * исходя из игрового поля 5x5
     */
    private static void humanTurn() {
        do {
            System.out.print("Введите координаты X и Y (от 1 до 5) через пробел >>> ");
            movePlayer[0] = SCANNER.nextInt() - 1;
            movePlayer[1] = SCANNER.nextInt() - 1;
        } while (!isCellValid() || !isCellEmpty());
        field[movePlayer[0]][movePlayer[1]] = DOT_HUMAN;
    }

    private static boolean isCellEmpty() {
        return field[movePlayer[0]][movePlayer[1]] == DOT_EMPTY;
    }

    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    private static boolean isCellValid() {
        return movePlayer[0] >= 0 && movePlayer[0] < fieldSizeX
                && movePlayer[1] >= 0 && movePlayer[1] < fieldSizeY;
    }

    private static void aiTurn() {
        do {
            movePlayer[0] = random.nextInt(fieldSizeX);
            movePlayer[1] = random.nextInt(fieldSizeY);
        } while (!isCellEmpty());
        field[movePlayer[0]][movePlayer[1]] = DOT_AI;
    }

    private static int countSellRightHorizontal(char c, int x, int y) {
        int count = 0;
        while (x < fieldSizeX) {
            if (field[x][y] == c) {
                count++;
                x++;
            } else break;
        }
        return count;
    }

    private static int countSellLeftHorizontal(char c, int x, int y) {
        int count = 0;
        while (x >= 0) {
            if (field[x][y] == c) {
                count++;
                x--;
            } else break;
        }
        return count;
    }

    private static int countSellBottomVertical(char c, int x, int y) {
        int count = 0;
        while (y < fieldSizeY) {
            if (field[x][y] == c) {
                count++;
                y++;
            } else break;
        }
        return count;
    }

    private static int countSellTopVertical(char c, int x, int y) {
        int count = 0;
        while (y >= 0) {
            if (field[x][y] == c) {
                count++;
                y--;
            } else break;
        }
        return count;
    }

    private static int countSellBottomLeftDiagonal(char c, int x, int y) {
        int count = 0;
        while (x < fieldSizeX && y < fieldSizeY) {
            if (field[x][y] == c) {
                count++;
                x++;
                y++;
            } else break;
        }
        return count;
    }
    private static int countSellTopLeftDiagonal(char c, int x, int y) {
        int count = 0;
        while (x >= 0 && y >= 0) {
            if (field[x][y] == c) {
                count++;
                x--;
                y--;
            } else break;
        }
        return count;
    }

    private static int countSellBottomRightDiagonal(char c, int x, int y) {
        int count = 0;
        while (x >= 0 && y < fieldSizeY) {
            if (field[x][y] == c) {
                count++;
                x--;
                y++;
            } else break;
        }
        return count;
    }

    private static int countSellTopRightDiagonal(char c, int x, int y) {
        int count = 0;
        while (x < fieldSizeX && y >= 0) {
            if (field[x][y] == c) {
                count++;
                x++;
                y--;
            } else break;
        }
        return count;
    }

    private static boolean checkWin(char c) {
        if (checkVertical(c)) return true;
        if (checkHorizontal(c)) return true;
        if (checkLeftDiagonal(c)) return true;
        return checkRightDiagonal(c);
    }

    private static boolean checkHorizontal(char c) {
        int count = 1;
        count += countSellRightHorizontal(c, movePlayer[0] + 1, movePlayer[1])
                + countSellLeftHorizontal(c, movePlayer[0] - 1, movePlayer[1]);
//        System.out.println("countHorizontal: " + count);
        return count >= WIN_COUNT;
    }

    private static boolean checkVertical(char c) {
        int count = 1;
        count += countSellBottomVertical(c, movePlayer[0], movePlayer[1] + 1)
                + countSellTopVertical(c, movePlayer[0], movePlayer[1] - 1);
//        System.out.println("countVertical: " + count);
        return count >= WIN_COUNT;
    }

    private static boolean checkLeftDiagonal(char c) {
        int count = 1;
        count += countSellBottomLeftDiagonal(c, movePlayer[0] + 1, movePlayer[1] + 1)
                + countSellTopLeftDiagonal(c, movePlayer[0] - 1, movePlayer[1] - 1);
        return count >= WIN_COUNT;
    }

    private static boolean checkRightDiagonal(char c) {
        int count = 1;
        count += countSellBottomRightDiagonal(c, movePlayer[0] - 1, movePlayer[1] + 1)
                + countSellTopRightDiagonal(c, movePlayer[0] + 1, movePlayer[1] - 1);
        return count >= WIN_COUNT;
    }

    private static boolean checkDraw() {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (isCellEmpty(i, j)) return false;
            }
        }
        return true;
    }

    private static boolean gameCheck(char c, String str) {
        if (checkWin(c)) {
            System.out.println(str);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }
}
