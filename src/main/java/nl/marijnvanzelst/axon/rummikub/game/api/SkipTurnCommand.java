package nl.marijnvanzelst.axon.rummikub.game.api;

import java.util.UUID;

public class SkipTurnCommand {

    private final UUID gameId;

    private final String player;

    public SkipTurnCommand(UUID gameId, String player) {
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
