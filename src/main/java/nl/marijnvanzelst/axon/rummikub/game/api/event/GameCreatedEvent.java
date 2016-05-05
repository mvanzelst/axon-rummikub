package nl.marijnvanzelst.axon.rummikub.game.api.event;

import java.util.UUID;

public class GameCreatedEvent {

    private final UUID gameId;

    public GameCreatedEvent(UUID gameId) {
        this.gameId = gameId;
    }

    public UUID getGameId() {
        return gameId;
    }
}
