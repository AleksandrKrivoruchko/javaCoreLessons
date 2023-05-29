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
//            movePlayer[0] = random.nextInt(fieldSizeX);
//            movePlayer[1] = random.nextInt(fieldSizeY);
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
        System.out.println("movePlayer: " + movePlayer[0] + ", " + movePlayer[1]);
        int winCount = 2;
        int[] tempMove = new int[2];
        tempMove[0] = movePlayer[0];
        tempMove[1] = movePlayer[1];
        if(checkHorizontal(DOT_HUMAN, winCount)) {
            System.out.println("checkHorizontal: " + movePlayer[0] + ", " + movePlayer[1]);
            movePlayer[0] = tempMove[0] + 1;
            if (isCellValid() && isCellEmpty()) {
                field[movePlayer[0]][movePlayer[1]] = DOT_AI;
                System.out.println("checkHorizontal1-2: " + movePlayer[0] + ", " + movePlayer[1]);
                return;
            }
            movePlayer[0] = tempMove[0] - 1;
            if (isCellValid() && isCellEmpty()) {
                field[movePlayer[0]][movePlayer[1]] = DOT_AI;
                System.out.println("checkHorizontal1-3: " + movePlayer[0] + ", " + movePlayer[1]);
                return;
            }
            movePlayer[0] = tempMove[0];
        }

        if (checkVertical(DOT_HUMAN, winCount)) {
            movePlayer[1] = tempMove[1] + 1;
            System.out.println("checkVertical: " + movePlayer[0] + ", " + movePlayer[1]);
            if (isCellValid() && isCellEmpty()) {
                field[movePlayer[0]][movePlayer[1]] = DOT_AI;
                System.out.println("checkVertical1: " + movePlayer[0] + ", " + movePlayer[1]);
                return;
            }
            movePlayer[1] = tempMove[1] - 1;
            if (isCellValid() && isCellEmpty()) {
                field[movePlayer[0]][movePlayer[1]] = DOT_AI;
                System.out.println("checkVertical2: " + movePlayer[0] + ", " + movePlayer[1]);
                return;
            }
            movePlayer[1] = tempMove[1];
        }

        if (checkLeftDiagonal(DOT_HUMAN, winCount)) {
            movePlayer[0] = tempMove[0] + 1;
            movePlayer[1] = tempMove[1] + 1;
            if (isCellValid() && isCellEmpty()) {
                    field[movePlayer[0]][movePlayer[1]] = DOT_AI;
                    return;
            }
            movePlayer[0] = tempMove[0] - 1;
            movePlayer[1] = tempMove[1] - 1;
            if (isCellValid() && isCellEmpty()) {
                        field[movePlayer[0]][movePlayer[1]] = DOT_AI;
                        return;
            }
            movePlayer[0] = tempMove[0];
            movePlayer[1] = tempMove[1];
        }

        if (checkRightDiagonal(DOT_HUMAN, winCount)) {
            movePlayer[0] = tempMove[0] - 1;
            movePlayer[1] = tempMove[1] + 1;
            if (isCellValid() && isCellEmpty()) {
                    field[movePlayer[0]][movePlayer[1]] = DOT_AI;
                    return;
            }
            movePlayer[0] = tempMove[0] + 1;
            movePlayer[1] = tempMove[1] - 1;
            if (isCellValid() && isCellEmpty()) {
                        field[movePlayer[0]][movePlayer[1]] = DOT_AI;
                        return;
            }
        }
        do {
            movePlayer[0] = random.nextInt(fieldSizeX);
            movePlayer[1] = random.nextInt(fieldSizeY);
        } while (!isCellEmpty());
        field[movePlayer[0]][movePlayer[1]] = DOT_AI;
    }

    private static boolean checkWin(char c) {
        if (checkVertical(c, WIN_COUNT)) return true;
        if (checkHorizontal(c, WIN_COUNT)) return true;
        if (checkLeftDiagonal(c, WIN_COUNT)) return true;
        if (checkRightDiagonal(c, WIN_COUNT)) return true;

        return false;
    }

    private static boolean checkHorizontal(char c, int winCount) {
        int count = 1;
        for (int i = movePlayer[0] + 1; i < fieldSizeX; i++) {
            if (field[i][movePlayer[1]] == c) {
                count++;
                if (count == winCount) {
                    return true;
                }
            } else {
                break;
            }
        }
        for (int i = movePlayer[0] - 1; i >=0; i--) {
            if (field[i][movePlayer[1]] == c) {
                count++;
                if (count == winCount) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    private static boolean checkVertical(char c, int winCount) {
        int count = 1;
        for (int i = movePlayer[1] + 1; i < fieldSizeY; i++) {
            if (field[movePlayer[0]][i] == c) {
                count++;
                if (count == winCount) {
                    return true;
                }
            } else {
                break;
            }
        }
        for (int i = movePlayer[1] - 1; i >=0; i--) {
            if (field[movePlayer[0]][i] == c) {
                count++;
                if (count == winCount) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    private static boolean checkLeftDiagonal(char c, int winCount) {
        int count = 1;
        for (int i = movePlayer[0] -1, j = movePlayer[1] - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (field[i][j] == c) {
                count++;
                if (count == winCount) {
                    return true;
                }
            } else {
                break;
            }
        }

        for (int i = movePlayer[0] + 1, j = movePlayer[1] + 1;
             i < fieldSizeX && j < fieldSizeY; i++, j++) {
            if (field[i][j] == c) {
                count++;
                if (count == winCount) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    private static boolean checkRightDiagonal(char c, int winCount) {
        int count = 1;
        for (int i = movePlayer[0] -1, j = movePlayer[1] + 1;
             i >= 0 && j < fieldSizeY; i--, j++) {
            if (field[i][j] == c) {
                count++;
                if (count == winCount) {
                    return true;
                }
            } else {
                break;
            }
        }

        for (int i = movePlayer[0] + 1, j = movePlayer[1] - 1;
             i < fieldSizeX && j >= 0; i++, j--) {
            if (field[i][j] == c) {
                count++;
                if (count == winCount) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
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
