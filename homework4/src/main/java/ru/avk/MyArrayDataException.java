package ru.avk;

public class MyArrayDataException extends MyArrayException{
    public MyArrayDataException(String message, int row, int column) {
        super(message, row, column);
    }

    @Override
    public String formatMessage() {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement el : this.getStackTrace()) {
            sb.append(el).append("\n");
        }
        return String.format("%s в ячейке [%d][%d]\n%s",
                this.getMessage(), row, column, sb);
    }
}
