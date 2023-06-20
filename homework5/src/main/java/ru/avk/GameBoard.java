package ru.avk;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Класс для 3 задания
 */
public class GameBoard {
    // Массив для сохранения игрового поля 3х3
    private final int[] boardInt = new int[9];

    private final int mask = 3;

    public GameBoard() {
        fillBoard();
    }

    public int[] getBoardInt() {
        return boardInt;
    }

    /**
     * Метод для вывода на консоль игрового поля
     * @param array массив представляющий игровое поле
     */
    public void printBoard(int[] array) {
        int count = 1;
        for (int i = 0; i < array.length; i++, count++) {
            System.out.print(convertIntToString(array[i]) + " ");
            if (count == mask) {
                count = 0;
                System.out.println();
            }
        }
    }

    /**
     * Метод для заполнения массива случайным образом
     */
    private void fillBoard() {
        Random random = new Random();
        for (int i = 0; i < boardInt.length; i++) {
            int n = random.nextInt(mask);
            boardInt[i] = n;
        }
    }

    /**
     * Метод для строкового представления ячейки игрового поля
     * @param n число представляющее ячейку
     *          0 - пустая, 1 - крестик, 2 - нолик
     * @return строку (E - пустая, Х - крестик, О - нолик)
     */
    private String convertIntToString(int n) {
        final int O = 2;
        final int X = 1;
        return switch (n) {
            case O -> "O";
            case X -> "X";
            default -> "E";
        };
    }

    /**
     * Метод для упаковки массива игрового поля в int
     * @return int
     */
    public int convertBoardToInt() {
        int result = 0;
        int shift = 16;
        for (int i = boardInt.length - 1; i >= 0; i--) {
            result += boardInt[i] << shift;
            shift -= 2;
        }
        return result;
    }

    /**
     * Метод для распаковки int в массив игрового поля
     * @param n int - число представляющее массив в трех байтах
     * @return массив игрового поля
     */
    public int[] convertIntToBoard(int n) {
        int[] array = new int[9];
        int shift = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = (n & (mask << shift)) >> shift;
            shift += 2;
        }
        return array;
    }

    /**
     * Метод сохраняет в файл упакованный массив
     * @param n int - число представляющее массив игрового поля
     * @param fileName - путь к файлу в который будут сохранены
     *                 три байта
     */
    public void save(int n, String fileName) {
        byte[] bytes = new byte[mask];
        int byteMask = 255;
        int shift = 16;
        for (int i = 0; i < mask; i++) {
            bytes[i] = (byte) ((n & (byteMask << shift)) >> shift);
            shift -= 8;
        }
       try (FileOutputStream fos = new FileOutputStream(fileName);){
           fos.write(bytes);
       }catch (IOException ex) {
           ex.printStackTrace();
       }
    }

    /**
     * Метод считывает из файла три байта и составляет из них
     * целое число (int)
     * @param fileNme путь к файлу
     * @return int
     */
    public int read(String fileNme) {
        int res = 0;
        int shift = 16;
        try (FileInputStream fis = new FileInputStream(fileNme)){
            int tmp = fis.read();
            while (tmp != -1) {
                res += tmp << shift;
                shift -= 8;
                tmp = fis.read();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
