package m01.StateMachine;

import java.util.Map;
import java.util.EnumMap;
import java.util.concurrent.CompletableFuture;

public final class GameStateMachine {
    private final Map<GameState, GameStateHandler> states;

    private GameStateHandler current;
    private GameState currentKey;

    public GameState getCurrentKey() {
        return currentKey;
    }

    public GameStateMachine(Iterable<GameStateHandler> states){
        this.states = new EnumMap<>(GameState.class);

        for(GameStateHandler state : states){
            this.states.put(state.getKey(), state);
        }
    }

    public CompletableFuture<Void> changeState(GameState next){
        GameStateHandler nextState = states.get(next);

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
