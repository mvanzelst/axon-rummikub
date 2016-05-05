package nl.marijnvanzelst.axon.rummikub.game.api.event;

import nl.marijnvanzelst.axon.rummikub.game.model.Board;

import java.util.UUID;

public class TurnSkippedEvent {

    private final UUID gameId;

    private final String player;

    private final Board newBoard;

    private final String nextPlayer;

    public TurnSkippedEvent(UUID gameId, String player, String nextPlayer, Board newBoard) {
        this.gameId = gameId;
        this.player = player;
        this.nextPlayer = nextPlayer;
        this.newBoard = newBoard;
    }

    public UUID getGameId() {
        return gameId;
    }

    public String getPlayer() {
        return player;
    }

    public Board getNewBoard() {
        return newBoard;
    }

    public String getNextPlayer() {
        return nextPlayer;
    }
}
