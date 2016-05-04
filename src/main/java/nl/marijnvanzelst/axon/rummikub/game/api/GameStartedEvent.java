package nl.marijnvanzelst.axon.rummikub.game.api;

import nl.marijnvanzelst.axon.rummikub.game.model.tile.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameStartedEvent {

    private final UUID gameId;
    private final List<Tile> stack;

    public GameStartedEvent(UUID gameId, List<Tile> stack) {
        this.gameId = gameId;
        this.stack = new ArrayList<>(stack);
    }

    public UUID getGameId() {
        return gameId;
    }

    public List<Tile> getStack() {
        return new ArrayList<>(stack);
    }
}
