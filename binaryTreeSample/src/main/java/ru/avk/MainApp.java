package ru.avk;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree binaryTree = new BinaryTree(ArrayUtils.getArrayV2());
        int answer;
        while (true) {
            binaryTree.print();
            System.out.println("Работа с бинарным деревом");
            System.out.println("=========================================\n");
            System.out.println("0. Завершение работы приложения");
            System.out.println("1. Сгенерировать новое дерево");
            System.out.println("2. Добавить новый узел");
            System.out.println("3. Найти узел по значению");
            System.out.println("4. Удалить узел по значению");
            System.out.print("Выберите пункт меню: ");
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                switch (answer) {
                    case 0 -> {
                        System.out.println("Завершение работы приложения");
                        scanner.close();
                        return;
                    }
                    case 1 -> binaryTree = new BinaryTree(ArrayUtils.getArray());
                    case 2 -> System.out.println(binaryTree.add(ProcessNumber(scanner)) ?
                            "Узел успешно добавлен." :
                            "Невозможно добавить новый узел в дерево." +
                            " Возможно, узел с подобным значением уже присутствует в дереве.");
                    case 3 -> System.out.println(binaryTree.contains(ProcessNumber(scanner)) ?
                            "Узел успешно найден." : "Узел не найден");
                    case 4 -> System.out.println(binaryTree.remove(ProcessNumber(scanner)) ?
                            "Узел успешно удален." : "Узел не найден.");
                    default -> System.out.println("Укажите корркктный пункт меню.");
                }
            } else {
                if(scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                System.out.println("Укажите корректное целое число.");
            }


        }
    }

    private static int ProcessNumber(Scanner scanner) {
        while (true) {
            System.out.print("Укажите число: ");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                System.out.println("Укажите корректное целое число.");
            }
        }
    }
}
