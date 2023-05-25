package ru.avk;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class ArrayUtils {
    private static final Random random = new Random();
    public static Iterator<Integer> getArray(int elementCount) {
        if (elementCount <= 0 || elementCount > 199) {
            throw new RuntimeException("Кол-во элементов должно быть в диапазоне от 1 до 199.");
        }
        HashSet<Integer> hashSet = new HashSet<>();
        while (hashSet.size() != elementCount) {
            hashSet.add(random.nextInt(-99, 100));
        }
        return hashSet.iterator();
    }

    public static Iterator<Integer> getArrayV2() {
        return Arrays.stream((new Integer[] {1, 2, 3, 4, 5, 6, 7, 8})).iterator();
    }

    public static Iterator<Integer> getArray() {
        return getArray(random.nextInt(10, 21));
    }
}
