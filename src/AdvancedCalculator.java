import java.util.Scanner;

public class AdvancedCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение, отделяя целые числа от 1 до 10 и операции пробелами в формате 'число1 операция число2 [операция число3]':");
        String input = scanner.nextLine();

        try {
            int result = calculate(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static int calculate(String input) throws IllegalArgumentException {
        String[] elements = input.split("\\s+");

        if (elements.length < 3 || elements.length % 2 == 0) {
            throw new IllegalArgumentException("Некорректное количество элементов");
        }

        // Первый проход для умножения и деления
        int result = getValidNumber(elements[0]);
        for (int i = 1; i < elements.length; i += 2) {
            char operation = elements[i].charAt(0);
            int nextNum = getValidNumber(elements[i + 1]);

            if (operation == '*' || operation == '/') {
                result = performOperation(result, operation, nextNum);
            }
        }

        // Второй проход для сложения и вычитания
        for (int i = 1; i < elements.length; i += 2) {
            char operation = elements[i].charAt(0);
            int nextNum = getValidNumber(elements[i + 1]);

            if (operation == '+' || operation == '-') {
                result = performOperation(result, operation, nextNum);
            }
        }

        return result;
    }

    private static int performOperation(int num1, char operation, int num2) throws IllegalArgumentException {
        switch (operation) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new IllegalArgumentException("Деление на ноль");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Некорректная операция: " + operation);
        }
    }

    private static int getValidNumber(String element) throws IllegalArgumentException {
        try {
            return Integer.parseInt(element);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некорректное число: " + element);
        }
    }
}
