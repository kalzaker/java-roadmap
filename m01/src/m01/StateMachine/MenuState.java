package m01.StateMachine;

import java.util.concurrent.CompletableFuture;

public class MenuState implements GameStateHandler {
    public GameState getKey() {
        return GameState.MENU;
    }

    public CompletableFuture<Void> onEnter(){
        System.out.println("Вход в MENU");
        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<Void> onExit(){
        System.out.println("Выход из MENU");
        return CompletableFuture.completedFuture(null);
    }
}
