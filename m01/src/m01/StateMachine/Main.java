package m01.StateMachine;

import java.util.List;

public class Main {
    public static void main(String[] args){
        GameStateMachine stateMachine = new GameStateMachine(List.of(new MenuState(), new GameplayState()));

        System.out.println("Переходим в MENU:");
        stateMachine.changeState(GameState.MENU);

        System.out.println("Переходим в GAMEPLAY:");
        stateMachine.changeState(GameState.GAMEPLAY);

        System.out.println("Переходим в MENU:");
        stateMachine.changeState(GameState.MENU);
    }
}
