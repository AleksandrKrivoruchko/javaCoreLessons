package ru.avk.cyclic_shift;

import ru.avk.inversion.InvertArray;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class MainApp {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arr = CyclicShiftArray.shift(array, 4);
        System.out.println(Arrays.toString(arr));
        int[] array1 = IntStream.range(1, 21)
                        .toArray();
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(CyclicShiftArray.shift(array, 93)));
        System.out.println(Arrays.toString(CyclicShiftArray.shift(array1, 43)));
        int[] tmp = new int[20];
        Random random = new Random();
        Arrays.setAll(tmp, x -> random.nextInt(0, 2));
        System.out.println(Arrays.toString(tmp));
        System.out.println();
        InvertArray.invert(tmp);
        System.out.println(Arrays.toString(tmp));
        System.out.println();
        InvertArray.invert1(tmp);
        System.out.println(Arrays.toString(tmp));
    }
}
