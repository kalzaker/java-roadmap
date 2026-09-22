package m01.StateMachine;

import java.util.List;

public class Main {
    public static void main(String[] args){
        System.out.println("== vs equals:"); // == сравнивает ссылки, а equals сравнивает значения
        stringComparison();
        System.out.println("Сравнение Integer и кэш -128..127:"); // для значений -128 до 127 используются кэшированные объекты, сравнение через == до 127 выдаст true, после false
        integerCache();
        System.out.println("Переполнение int:"); // не выдает ошибку, а зацикливает значение
        intOverflow();
        System.out.println("Целочисленное деление:"); // если делить int на int, результат тоже будет int
        integerDivision();
        System.out.println("Арифметика char:"); // при арифметике char становится int
        charArithmetic();
        System.out.println("Неизменяемость String:"); // string неизменяемый, concat() создаст новую строку, а старая останется прежней, сперва неправильный потом правильный вариант
        stringImmutable();
    }

    static void stringComparison(){
        String a = new String("Hello");
        String b = new String("Hello");

        System.out.println(a == b);
        System.out.println(a.equals(b));
    }

    static void integerCache() {
        Integer a = 127;
        Integer b = 127;

        Integer c = 128;
        Integer d = 128;

        System.out.println(a == b);
        System.out.println(c == d);
    }

    static void intOverflow() {
        int x = Integer.MAX_VALUE + 1;
        System.out.println(x);
    }

    static void integerDivision() {
        int x = 5 / 2;
        System.out.println(x);
    }

    static void charArithmetic(){
        char c = 'A';
        System.out.println(c + 1);
    }

    static void stringImmutable() {
        String s = "Hello";
        s.concat(" World");

        System.out.println(s);

        s = s.concat(" World");
        System.out.println(s);
    }
}
