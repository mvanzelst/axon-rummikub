package nl.marijnvanzelst.axon.rummikub.game.api.event;

import java.util.UUID;

public class PlayerSeatedEvent {

    private final UUID gameId;

    private final String playerName;

    public PlayerSeatedEvent(UUID gameId, String playerName) {
        this.gameId = gameId;
        this.playerName = playerName;
    }

    public UUID getGameId() {
        return gameId;
    }

    public String getPlayerName() {
        return playerName;
    }
}
