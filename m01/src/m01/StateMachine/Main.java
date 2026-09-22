package m01.StateMachine;

import java.util.List;

public class Main {
    static void main(String[] args){
        GameStateMachine stateMachine = new GameStateMachine(List.of(new MenuState(), new GameplayState()));

        System.out.println("Переходим в MENU:");
        stateMachine.changeState(GameState.MENU);

        System.out.println("Переходим в GAMEPLAY:");
        stateMachine.changeState(GameState.GAMEPLAY);

        System.out.println("Переходим в MENU:");
        stateMachine.changeState(GameState.MENU);

        stringComparison();
        integerCache();
        intOverflow();
        integerDivision();
        charArithmetic();
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
