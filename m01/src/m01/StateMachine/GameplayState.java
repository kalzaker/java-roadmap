package m01.StateMachine;

import java.util.concurrent.CompletableFuture;

public class GameplayState implements GameStateHandler {
    public GameState getKey() {
        return GameState.GAMEPLAY;
    }

    public CompletableFuture<Void> onEnter(){
        System.out.println("Вход в GAMEPLAY");
        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<Void> onExit(){
        System.out.println("Выход из GAMEPLAY");
        return CompletableFuture.completedFuture(null);
    }
}