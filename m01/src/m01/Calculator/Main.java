package m01.Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.print("Выберите операцию (+, -, *, /, %, ^): ");
            String operation = scanner.nextLine();

            try {
                System.out.print("Введите первое число: ");
                double a = Double.parseDouble(scanner.nextLine());

                System.out.print("Введите второе число: ");
                double b = Double.parseDouble(scanner.nextLine());

                double result;

                switch(operation){
                    case "+" -> result = add(a, b);
                    case "-" -> result = substract(a, b);
                    case "*" -> result = multiply(a, b);
                    case "/" -> result = divide(a, b);
                    case "%" -> result = remainder(a, b);
                    case "^" -> result = power(a, b);
                    default -> {
                        System.out.println("Неизвестная операция.");
                        continue;
                    }
                }

                result = Math.round(result * 1000000.0) / 1000000.0; // чтобы убрать вот такие результаты 2.6 + 5.2 = 7.800000000000001

                if(result == (long) result){
                    System.out.println("Результат: " + (long) result);
                } else {
                    System.out.println("Результат: " + result);
                }
            } catch(NumberFormatException ex) {
                System.out.println("Некорректное число");
            } catch(ArithmeticException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static double add(double a, double b){
        return a + b;
    }

    public static double substract(double a, double b){
        return a - b;
    }

    public static double multiply(double a, double b){
        return a * b;
    }

    public static double divide(double a, double b){
        if(b == 0){
            throw new ArithmeticException("Деление на ноль.");
        }

        return a / b;
    }

    public static double remainder(double a, double b){
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль.");
        }

        return a % b;
    }

    public static double power(double a, double b){
        return Math.pow(a, b);
    }
}


