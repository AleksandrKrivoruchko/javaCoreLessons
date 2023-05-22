package ru.avk.simple_math_operations;

public class Decorator {
	public static String operationToString(int a, int b, int result, String sign) {
		return String.format("%d %s %d = %d", a, sign, b, result);
	}
}