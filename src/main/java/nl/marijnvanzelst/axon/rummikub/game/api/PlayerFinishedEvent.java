package nl.marijnvanzelst.axon.rummikub.game.api;

import java.util.UUID;

public class PlayerFinishedEvent {

    private final UUID gameId;
    private final String player;

    public PlayerFinishedEvent(UUID gameId, String player) {
        this.gameId = gameId;
        this.player = player;
    }

    public UUID getGameId() {
        return gameId;
    }

    public String getPlayer() {
        return player;
    }
}
