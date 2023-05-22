package ru.avk.simple_math_operations;

/**
 * Класс содержащий методы для сложения и вычетания
 * целых чисел
 */
public class SimpleMathOperations {
	/**
	 * Метод sum - без проверки входных параметров
	 * 
	 * @param int a - целое число
	 * @param int b - целое число
	 * @return int - результат сложения целое число без проверки переполнения
	 */
	public static int sum(int a, int b) {
		return a + b;
	}

	/**
	 * Метод sub - без проверки входных параметров
	 * 
	 * @param int a - целое число
	 * @param int b - целое число
	 * @return int - разность a - b, целое число без проверки переполнения
	 */
	public static int sub(int a, int b) {
		return a - b;
	}

	public static int mul(int a, int b) {
		return a * b;
	}

	public static int div(int a, int b) {
		return a / b;
	}
}