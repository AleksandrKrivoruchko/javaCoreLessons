package ru.avk;

import java.io.File;

public class MainApp {
    public static void main(String[] args) {
        Tree.print(new File("."), "", true);
        System.out.println(Backup.backup("./src"));
    }
}
