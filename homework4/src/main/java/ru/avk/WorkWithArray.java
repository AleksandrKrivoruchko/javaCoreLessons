package ru.avk;

import javax.lang.model.util.Elements;
import java.util.Random;

public class WorkWithArray {
    private final String[][] array;
    private final int row;
    private final int column;

    public WorkWithArray(int row, int column, boolean flag) {
        this.row = row;
        this.column = column;
        array = new String[row][column];
        fillArray(flag);
    }

    public String[][] getArray() {
        return array;
    }

    public void setElement(String value, int i, int j) {
        array[i][j] = value;
    }

    /**
     * Метод для подсчета суммы эдементов двумерного массива
     * @param flag если true, то проверяется равен ли размер массива 4х4
     * @return сумму элементов массива
     * @throws MyArrayException выбрасывается MyArraySizeException если flag true и размер
     * массива не равен 4х4 или пробрасывает MyArrayDataException от метода sum
     */
    public int sumOfArrayElements(boolean flag) throws MyArrayException {
        if (flag) {
            if (row != column || row != 4) {
                throw new MyArraySizeException("Размер массива не соответствует ожидаемому",
                        row, column);
            }
        }
        return sum();
    }

    /**
     * Метод для подсчета суммы эдементов двумерного массива
     * @return сумму элементов массива
     * @throws MyArrayDataException выбрасывается если элемент не целое число
     */
    private int sum() throws MyArrayDataException{
        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException(String.format(
                            "Неверный формат данных %s", array[i][j]),
                            i, j);
                }
            }
        }
        return sum;
    }

    /**
     * Метод заполняет двумерный массив строковыми значениями чисел
     * @param flag если true то одно из чисел заменяется на "N"
     */
    public void fillArray(boolean flag) {
        Random random = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                array[i][j] = String.valueOf(random.nextInt(10));
            }
        }
        if (flag) {
            array[random.nextInt(row)][random.nextInt(column)] = "N";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+| ");
        for (int i = 0; i < column; i++) {
            sb.append(i).append(" | ");
        }
        sb.append("\n");
        for (int k = 0; k < row; k++) {
            for (int i = 0; i < column * 4 + 2; i++) {
                sb.append("-");
            }
            sb.append("\n").append(k).append("| ");
            for (int m = 0; m < column; m++) {
                sb.append(array[k][m]).append(" | ");
            }
            sb.append("\n");
        }
        sb.append("-".repeat(Math.max(0, column * 4 + 2)));
        return sb.toString();
    }
}
