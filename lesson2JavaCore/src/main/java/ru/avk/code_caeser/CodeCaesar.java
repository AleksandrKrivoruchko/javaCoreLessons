package ru.avk.code_caeser;

import java.util.Scanner;

public class CodeCaesar {
    private final String strLow = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private final String strUpper = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private final int lenAlphabet = 32;

    public String getStrLow() {
        return strLow;
    }

    public String getStrUpper() {
        return strUpper;
    }

    public String coderAndDecoder(String str, int n, boolean flagCode) {
        StringBuilder sb = new StringBuilder();
        if (n > lenAlphabet) {
            n = n % lenAlphabet;
        }
        if (flagCode) {
            coderSb(str, n, sb);
        } else {
            decoderSb(str, n, sb);
        }
        return sb.toString();
    }

    private void decoderSb(String str, int n, StringBuilder sb) {
        for (char c : str.toCharArray()) {
            if (strLow.indexOf(c) != -1) {
                sb.append(decoder(strLow, n, c));
            } else {
                if (strUpper.indexOf(c) < 0) {
                    sb.append(c);
                } else {
                    sb.append(decoder(strUpper, n, c));
                }
            }
        }
    }

    private char decoder(String str, int n, char c) {
        int tmp = str.indexOf(c);

        if (tmp < 0) {
            return c;
        }
        if (tmp - n  < 0) {
//            System.out.println(str.charAt(lenAlphabet + 1 + (tmp - n)));
            return str.charAt(lenAlphabet + 1 + (tmp - n));
        }
        return str.charAt(tmp - n);
    }

    private void coderSb(String str, int n, StringBuilder sb) {
        for (char c : str.toCharArray()) {
            if (strLow.indexOf(c) != -1) {
                sb.append(coder(strLow, n, c));
            } else {
                if (strUpper.indexOf(c) < 0) {
                    sb.append(c);
                } else {
                    sb.append(coder(strUpper, n, c));
                }
            }
        }
    }

    private char coder(String str, int n, char c) {
        int tmp = str.indexOf(c);
        if (tmp < 0) {
            return c;
        }

        if (tmp + n > lenAlphabet) {
            return str.charAt(tmp + n - 1 - lenAlphabet);
        }
        return str.charAt(tmp + n);
    }
}
