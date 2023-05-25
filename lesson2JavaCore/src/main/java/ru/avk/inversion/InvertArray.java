package ru.avk.inversion;

import java.util.Arrays;

public class InvertArray {
    public static void invert(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] ^ 1;
        }
    }

    public static void invert1(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] == 1 ? 0 : 1;
        }
    }
}
