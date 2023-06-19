package ru.avk;

import java.io.File;

public class MainApp {
    public static void main(String[] args) {
//        System.out.println(Backup.backup("./src", "./backup"));
//        System.out.println("*******************************");
//        Tree.print(new File("./backup"), "", true);
        GameBoard gb = new GameBoard();
        gb.printBoard();
    }
}
