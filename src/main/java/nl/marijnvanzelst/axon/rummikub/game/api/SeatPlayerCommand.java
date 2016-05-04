package nl.marijnvanzelst.axon.rummikub.game.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class SeatPlayerCommand {

    @TargetAggregateIdentifier
    private final UUID gameId;

    private final String playerName;

    public SeatPlayerCommand(UUID gameId, String playerName) {
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
