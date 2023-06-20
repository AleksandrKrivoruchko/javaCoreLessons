package ru.avk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Класс  для 1 задания
 */
public class Backup {

    /**
     * Метод копирует из одного директория в другой
     * @param sourceDir директория откуда копируются файлы
     * @param backupDir директория куда копируются файлы
     * @return сообщение о работе метода
     */
    public static String backup(String sourceDir, String backupDir) {
        File sourceFile = new File(sourceDir);
        if (sourceFile.exists()) {
            if (!sourceFile.isDirectory()) {
                return String.format("%s - не директория!", sourceDir);
            }
        } else {
            return String.format("Директория %s не существует!",
                    sourceDir);
        }
        File[] sourceFiles = sourceFile.listFiles();
        if (sourceFiles == null || sourceFiles.length < 1) {
            return String.format("Директория %s пустая, нечего сохранять!",
                    sourceDir);
        }
        File backupFile = new File(backupDir);
        if (!backupFile.exists()) {
            if (!backupFile.mkdir()) {
                return String.format("Не удалось создать директорию %s",
                        backupDir);
            }
        }
        try {
            copy(backupDir, sourceFiles);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return String.format("Файлы скопированы в %s", backupDir);
    }

    /**
     * Метод читает файлы из массива sourceFiles и записывает их
     * по пути backupDir
     * @param backupDir путь куда записывать
     * @param sourceFiles массив файлов для копирования
     * @throws IOException ошибки при работе с файлами
     */
    private static void copy(String backupDir, File[] sourceFiles) throws IOException {
        for (File file : sourceFiles) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                String newPath = backupDir +"/" + file.getName();
                File newDir = new File(newPath);
                if (!newDir.exists()) {
                    if (newDir.mkdir()) {
                        if (files != null && files.length > 0) {
                            copy(newPath, files);
                        }
                    }
                } else {
                    if (files != null && files.length > 0) {
                        copy(newPath, files);
                    }
                }
                continue;
            }
            try(FileInputStream fis = new FileInputStream(file)) {
                try (FileOutputStream fos = new FileOutputStream(backupDir +
                        "/" + file.getName())){
                    int res = fis.read();
                    while (res != -1) {
                        fos.write(res);
                        res = fis.read();
                    }
                }
            }
        }
    }
}
