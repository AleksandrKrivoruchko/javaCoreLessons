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

    public static void main(String[] args) {
        do {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, WIN_COUNT,"Вы победили!")) {
                    break;
                }
                aiTurn();
                printField();
                if (gameCheck(DOT_AI, WIN_COUNT,"Компьютер победил!")) {
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
        int x;
        int y;
        do {
            System.out.print("Введите координаты X и Y (от 1 до 5) через пробел >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Проверка, является ли ячейка игрового поля пустой
     * @param x координата ячейки
     * @param y координата ячейки
     * @return результат проверки
     */
    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка валидности ячейки
     * @param x координата ячейки
     * @param y координата ячейки
     * @return результат проверки
     */
    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX
                && y >= 0 && y < fieldSizeY;
    }

    /**
     * Ход компьютера
     */
    private static void aiTurn() {
        // Побеждает ли компьютер в текущем ходе (при выигрышной комбинации WIN_COUNT) ?
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[x][y] == DOT_EMPTY) {
                    field[x][y] = DOT_AI;
                    if (checkWin(DOT_AI, WIN_COUNT)) {
                        return;
                    } else {
                        field[x][y] = DOT_EMPTY;
                    }
                }
            }
        }

        // Побеждает ли игрок на текущий момент при выигрышной комбинации
        // WIN_COUNT - 1 ?
        boolean f = checkWin(DOT_HUMAN, WIN_COUNT - 1);
        // Теперь, снова пойдем по всем свободным ячейкам игрового поля,
        // если игрок уже побеждает при выигрышной комбинации WIN_COUNT - 1,
        // компьютер будет действовать на опережение, попытается "подпортить"
        // человеку выигрышную комбинацию
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[x][y] == DOT_EMPTY) {
                    field[x][y] = DOT_HUMAN;
                    if (checkWin(DOT_HUMAN, WIN_COUNT -(f ? 0 : 1))) {
                        field[x][y] = DOT_AI;
                        return;
                    } else {
                        field[x][y] = DOT_EMPTY;
                    }
                }
            }
        }

        // Ни человек, ни компьютер не выигрывают, значит, компьютер
        // ставит фишку случайным образом
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    /**
     * Проверка победы игрока
     * @param dot фишка игрока (человек или компьютер)
     * @param winCount количество фишек побуды
     * @return результат проверки
     */
    private static boolean checkWin(char dot, int winCount) {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[x][y] == dot) {
                    if (checkXY(x, y, 1, winCount) ||
                    checkXY(x, y, -1, winCount) ||
                    checkDiagonal(x, y, -1, winCount) ||
                    checkDiagonal(x, y, 1, winCount)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Проверка на ничью
     * @return результат проверки
     */
    private static boolean checkDraw() {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (isCellEmpty(i, j)) return false;
            }
        }
        return true;
    }

    /**
     * Проверка выигрыша игрока (человек или компьютер)
     * горизонтали + вправо/вертикали + вниз
     * @param x начальная координата фишки
     * @param y начальная координата фишки
     * @param dir направление проверки (-1 => горизонтали + вправо/ 1 => вертикали + вниз)
     * @param win выигрышная комбинация
     * @return результат проверки
     */
    private static boolean checkXY(int x, int y, int dir, int win) {
        char c = field[x][y]; // получим текущую фишку (игрок или компьютор)
        // Пройдем по всем ячейкам от начальной координаты
        // по горизонтали вправо и по вертикали вниз (в зависимости от значения dir)
        for (int i = 1; i < win; i++) {
            if (dir > 0 && (!isCellValid(x + i, y) || c != field[x + i][y])) {
                return false;
            } else if (dir < 0 && (!isCellValid(x, y + i) || c != field[x][y + i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDiagonal(int x, int y, int dir, int win) {
        char c = field[x][y]; // получить текущую фишку (игрок или компьютер)
        // Пройдем по всем ячейкам от начальной координаты
        // по диагонали вверх и по диагонали вниз (в зависимости от значения dir)
        for (int i = 1; i < win; i++) {
            if (!isCellValid(x + i, y + i * dir) || c != field[x + i][y + i * dir]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Метод проверки состояния игры
     * @param dot фишка игрока (человек/компьютер)
     * @param win выигрышная комбинация
     * @param str победное сообщение
     * @return результат проверки
     */
    private static boolean gameCheck(char dot, int win, String str) {
        if (checkWin(dot, win)) {
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
