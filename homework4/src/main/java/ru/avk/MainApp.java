package ru.avk;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        WorkWithArray workWithArray1 = new WorkWithArray(5, 6, false);
        WorkWithArray workWithArray2 = new WorkWithArray(4, 4, true);

        try {
            workWithArray1.sumOfArrayElements(true);
        } catch (MyArrayException ex) {
            System.out.println(ex.formatMessage());
            System.out.println(workWithArray1);
        }

        try {
            System.out.println("Сумма элементов массива равна " +
                    workWithArray1.sumOfArrayElements(false));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(workWithArray2.sumOfArrayElements(true));
        } catch (MyArrayException ex) {
            System.out.println(ex.formatMessage());
            System.out.println(workWithArray2);
            workWithArray2.setElement("0", ex.row, ex.column);
        }

        System.out.println("\n---------------------------------------------");
        System.out.println("Исправленный массив:\n");
        System.out.println(workWithArray2);
        try {
            System.out.println("Сумма элементов массива равна " +
                    workWithArray2.sumOfArrayElements(true));
        } catch (MyArrayException ex) {
            ex.printStackTrace();
        }
    }
}
