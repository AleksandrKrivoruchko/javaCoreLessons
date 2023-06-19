package ru.avk;

import java.io.File;
import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
//        System.out.println(Backup.backup("./src", "./backup"));
//        System.out.println("*******************************");
//        Tree.print(new File("./backup"), "", true);
        GameBoard gb = new GameBoard();
        gb.printBoard(gb.getBoardInt());
        int n = gb.convertBoardToInt();
        System.out.println(n);
        gb.printBoard(gb.convertIntToBoard(n));
        System.out.println(Arrays.toString(gb.getBoardInt()));
        System.out.println(Arrays.toString(gb.convertIntToBoard(n)));
    }
}
