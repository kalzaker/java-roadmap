import java.util.Map;
import java.util.EnumMap;
import java.util.concurrent.CompletableFuture;

public final class GameStateMachine {
    private final Map<GameState, IGameState> states;

    private IGameState current;
    private GameState currentKey;

    public GameState getCurrentKey() {
        return currentKey;
    }

    public GameStateMachine(Iterable<IGameState> states){
        this.states = new EnumMap<>(GameState.class);

        for(IGameState state : states){
            this.states.put(state.getKey(), state);
        }
    }

    public CompletableFuture<Void> changeState(GameState next){
        IGameState nextState = states.get(next);

        if(nextState == null) {
            throw new IllegalStateException("Состояние " + next + " не зарегистрировано");
        }

        CompletableFuture<Void> exitFuture =
                current != null
                ? current.onExit()
                : CompletableFuture.completedFuture(null);

        return exitFuture.thenCompose(v -> {
            current = nextState;
            currentKey = next;

            return current.onEnter();
        });
    }
}
