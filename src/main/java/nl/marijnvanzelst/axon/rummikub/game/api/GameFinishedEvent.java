package nl.marijnvanzelst.axon.rummikub.game.api;

import java.util.UUID;

public class GameFinishedEvent {

    private final UUID gameId;

    public GameFinishedEvent(UUID gameId) {
        this.gameId = gameId;
    }

    public UUID getGameId() {
        return gameId;
    }
}
