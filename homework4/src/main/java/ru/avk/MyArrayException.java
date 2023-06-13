package ru.avk;

public abstract class MyArrayException extends Exception {
    protected int row;
    protected int column;

    public MyArrayException(String message, int row, int column) {
        super(message);
        this.row = row;
        this.column = column;
    }

    public abstract String formatMessage();
}
