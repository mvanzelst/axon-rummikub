package nl.marijnvanzelst.axon.rummikub.game.api;

import nl.marijnvanzelst.axon.rummikub.game.model.Board;
import nl.marijnvanzelst.axon.rummikub.game.model.tile.Tile;

import java.util.List;
import java.util.UUID;

/**
 * Created by marijn on 3-5-16.
 */
public class PlayTilesCommand {

    private final UUID gameId;

    private final Board newBoard;

    private final String player;

    private final List<Tile> newPlayerTiles;

    public PlayTilesCommand(UUID gameId, Board newBoard, String player, List<Tile> newPlayerTiles) {
        this.gameId = gameId;
        this.newBoard = newBoard;
        this.player = player;
        this.newPlayerTiles = newPlayerTiles;
    }

    public UUID getGameId() {
        return gameId;
    }

    public Board getNewBoard() {
        return newBoard;
    }

    public String getPlayer() {
        return player;
    }

    public List<Tile> getNewPlayerTiles() {
        return newPlayerTiles;
    }
}
