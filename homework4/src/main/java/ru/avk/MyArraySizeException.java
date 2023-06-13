package ru.avk;

public class MyArraySizeException extends MyArrayException{
    public MyArraySizeException(String message, int row, int column) {
        super(message, row, column);
    }

    @Override
    public String formatMessage() {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement el : this.getStackTrace()) {
            sb.append(el).append("\n");
        }
        return String.format(
                "%s\nРазмер массива должен быть 4х4\nВходной массив размером %dx%d\n%s",
                this.getMessage(), row, column, sb);
    }
}
