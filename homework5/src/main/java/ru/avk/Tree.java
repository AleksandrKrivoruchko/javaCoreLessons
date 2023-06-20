package ru.avk;

import java.io.File;

/**
 * Класс для 2 задания
 */
public class Tree {
    /**
     * Метод для вывода в консоль дерева директории
     * @param file объект File созданный для директории
     * @param indent строка со смещением и разделительными знаками
     * @param isLast признак, если файл последний то true, иначе false
     */
    public static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│  ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null) {
            return;
        }

        int subDirTotal = 0;
        int fileTotal = 0;
        for (File value : files) {
            if (value.isDirectory()) {
                subDirTotal++;
            } else {
                fileTotal++;
            }
        }

        int subDirCounter = 0;
        int fileCounter = 0;
        for (File value : files) {
            if (value.isDirectory()) {
                print(value, indent, subDirCounter == subDirTotal - 1);
                subDirCounter++;
            } else {
                print(value, indent, fileCounter == fileTotal - 1 &&
                        subDirCounter + fileCounter == files.length - 1);
                fileCounter++;
            }
        }
    }
}
