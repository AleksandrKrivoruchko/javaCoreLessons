package ru.avk;

import javax.lang.model.util.Elements;
import java.util.Random;

public class WorkWithArray {
    private String[][] array;
    private int row;
    private int column;

    public WorkWithArray(int row, int column, boolean flag) {
        this.row = row;
        this.column = column;
        array = new String[row][column];
        fillArray(flag);
    }

    public String[][] getArray() {
        return array;
    }
    public int sumOfArrayElements() throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (row != column || row != 4) {
            throw new MyArraySizeException("Размер массива не соответствует ожидаемому",
                    row, column);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException(String.format(
                            "Неверный формат данных %s", array[i][j]),
                            i, j, ex.getStackTrace());
                }
            }
        }
        return sum;
    }

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
