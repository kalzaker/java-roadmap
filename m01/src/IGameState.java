import java.util.concurrent.CompletableFuture;

public interface IGameState {
    GameState getKey();
    CompletableFuture<Void> onEnter();
    CompletableFuture<Void> onExit();
}
