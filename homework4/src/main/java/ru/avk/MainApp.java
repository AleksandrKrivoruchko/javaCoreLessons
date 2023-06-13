package ru.avk;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        WorkWithArray workWithArray = new WorkWithArray(4, 4, false);
        WorkWithArray workWithArray1 = new WorkWithArray(5, 6, false);
        WorkWithArray workWithArray2 = new WorkWithArray(4, 4, true);
        try {
            System.out.println(workWithArray);
            System.out.println(workWithArray.sumOfArrayElements());

            System.out.println("********************************************");
            workWithArray1.sumOfArrayElements();

        } catch (MyArraySizeException ex) {
            System.out.println(ex.formatMessage());
            System.out.println(workWithArray1);
        } catch (MyArrayDataException ex) {
            System.out.println(ex.formatMessage());
        }
        try {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(workWithArray2.sumOfArrayElements());
        } catch (MyArrayException ex) {
            System.out.println(ex.formatMessage());
            System.out.println(workWithArray2);
        }

    }
}
