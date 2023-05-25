package ru.avk.code_caeser;

import ru.avk.code_caeser.CodeCaesar;

public class MainApp {
    public static void main(String[] args) {
        CodeCaesar caesar = new CodeCaesar();
        String str = "Пробная строка";
        int n = 45;
        System.out.println(str);
        String strCod = caesar.coderAndDecoder(str, n, true);
        System.out.println(strCod);
        System.out.println(caesar.coderAndDecoder(strCod, n, false));
    }
}
