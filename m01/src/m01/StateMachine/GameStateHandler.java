package m01.StateMachine;

import java.util.concurrent.CompletableFuture;

public interface GameStateHandler {
    GameState getKey();
    CompletableFuture<Void> onEnter();
    CompletableFuture<Void> onExit();
}
