package nl.marijnvanzelst.axon.rummikub.game.api;

import java.util.UUID;

/**
 * Created by marijn on 3-5-16.
 */
public class TurnSkippedEvent {

    private final UUID gameId;

    private final String player;

    public TurnSkippedEvent(UUID gameId, String player) {
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
