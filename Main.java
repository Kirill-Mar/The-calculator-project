package Testcalc;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите числа и математическую операцию: ");
        String expression = scanner.nextLine();
        try {
            String result = calc(expression);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        String[] operands = input.split("[+\\-*/]");
        if (operands.length != 2) {
            throw new Exception("Должно быть два операнда");
        }

        String operation = detectOperation(input);
        if (operation == null) {
            throw new Exception("Неподдерживаемая математическая операция");
        }

        String[] numbers = splitNumbers(input);
        if (numbers.length != 2 || !input.matches("^\\d+[-+*/]\\d+$")) {
            throw new Exception("Некорректный формат математической операции");
        }
        try {
            int a = Integer.parseInt(numbers[0]);
            int b = Integer.parseInt(numbers[1]);

            if (!isInRange(a) || !isInRange(b)) {
                throw new Exception("Калькулятор принимает только числа от 1 до 10 включительно");
            }

            int result = performOperation(a, b, operation);

            return String.valueOf(result);
        } catch (NumberFormatException e) {
            throw new Exception("Некорректный формат числа");
        }
    }

    private static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    private static String[] splitNumbers(String expression) {
        return expression.split("[+\\-*/]");
    }

    private static int performOperation(int a, int b, String operation) {
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Деление на ноль недопустимо");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция");
        }
    }

    private static boolean isInRange(int num) {
        return num >= 1 && num <= 10;
    }
}
