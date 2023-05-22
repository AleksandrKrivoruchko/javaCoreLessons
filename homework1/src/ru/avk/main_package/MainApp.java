package ru.avk.main_package;

import ru.avk.simple_math_operations.Decorator;
import ru.avk.simple_math_operations.SimpleMathOperations;

/**
 * Класс содержащий метод main
 */
public class MainApp {
	/**
	 * Точка входа в приложение
	 * 
	 * @param String[] args - массив строк
	 */
	public static void main(String[] args) {
		int a = 4;
		int b = 2;
		int c = SimpleMathOperations.sum(a, b);
		System.out.println(Decorator.operationToString(a, b, c, "+"));
		c = SimpleMathOperations.sub(a, b);
		System.out.println(Decorator.operationToString(a, b, c, "-"));
		c = SimpleMathOperations.mul(a, b);
		System.out.println(Decorator.operationToString(a, b, c, "*"));
		c = SimpleMathOperations.div(a, b);
		System.out.println(Decorator.operationToString(a, b, c, "/"));
	}
}