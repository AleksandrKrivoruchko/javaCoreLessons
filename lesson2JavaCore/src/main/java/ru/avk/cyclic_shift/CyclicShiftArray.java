package ru.avk.cyclic_shift;

import java.util.Arrays;

public class CyclicShiftArray {
    public static int[] shift(int[] array, int n) {
        int len = array.length;
        if (n > len) {
            n = n % len;
        }
        int[] result = new int[len];
        int j = 0;
        for (int i = len - n;i < len ; i++, j++) {
            result[j] = array[i];
        }
        for (int i = 0; i < len -n; i++, j++) {
            result[j] = array[i];
        }
        return result;
    }
}
