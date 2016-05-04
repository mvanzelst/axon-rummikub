package nl.marijnvanzelst.axon.rummikub.game.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class CreateGameCommand {

    @TargetAggregateIdentifier
    private final UUID gameId;

    public CreateGameCommand(UUID gameId) {
        this.gameId = gameId;
    }

    public UUID getGameId() {
        return gameId;
    }
}
