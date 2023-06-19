package ru.avk;

import java.io.*;

public class Backup {
    public static String backup(String sourceDir) {
        File sourceFile = new File(sourceDir);
        if(!sourceFile.isDirectory()) {
            return String.format("%s - не директория!", sourceDir);
        }
        File[] sourceFiles = sourceFile.listFiles();
        if (sourceFiles == null || sourceFiles.length < 1) {
            return String.format("В директория %s пустая, нечего сохранять!",
                    sourceDir);
        }
        String backupDir = "./backup";
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

    private static void copy(String backupDir, File[] sourceFiles) throws IOException {
        for (File file : sourceFiles) {
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
