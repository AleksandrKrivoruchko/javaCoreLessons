package ru.avk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Backup {
    public static String backup(String sourceDir) {
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
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                String newPath = backupDir +"/" + file.getName();
                File newDir = new File(newPath);
                if (newDir.mkdir()) {
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
