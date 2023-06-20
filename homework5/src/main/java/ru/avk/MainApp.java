package ru.avk;

import java.io.File;

public class MainApp {
    public static void main(String[] args) {
        System.out.println(Backup.backup("./src", "./backup"));
        System.out.println("*******************************");
        Tree.print(new File("./backup"), "", true);
        GameBoard gb = new GameBoard();
        System.out.println("++++++++++++++++++++++++");
        System.out.println("Исходное поле:");
        gb.printBoard(gb.getBoardInt());
        int n = gb.convertBoardToInt();
        gb.save(n, "board.txt");
        System.out.println("++++++++++++++++++++++++");
        System.out.println("Поле считанное из файла:");
        n = gb.read("board.txt");
        gb.printBoard(gb.convertIntToBoard(n));
    }
}
