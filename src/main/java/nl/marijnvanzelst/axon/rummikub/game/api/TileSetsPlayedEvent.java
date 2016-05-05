package nl.marijnvanzelst.axon.rummikub.game.api;

import nl.marijnvanzelst.axon.rummikub.game.model.Board;
import nl.marijnvanzelst.axon.rummikub.game.model.tile.TileSet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TileSetsPlayedEvent {

    private final UUID gameId;
    private final String player;
    private final List<TileSet> tileSets;
    private final Board newBoard;
    private final String nextPlayer;

    public TileSetsPlayedEvent(UUID gameId, String player, List<TileSet> tileSets, String nextPlayer, Board newBoard) {
        this.gameId = gameId;
        this.player = player;
        this.nextPlayer = nextPlayer;
        this.tileSets = new ArrayList<>(tileSets);;
        this.newBoard = newBoard;
    }

    public UUID getGameId() {
        return gameId;
    }

    public String getPlayer() {
        return player;
    }

    public List<TileSet> getTileSets() {
        return new ArrayList<>(tileSets);
    }

    public Board getNewBoard() {
        return newBoard;
    }

    public String getNextPlayer() {
        return nextPlayer;
    }
}
