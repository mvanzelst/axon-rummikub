package nl.marijnvanzelst.axon.rummikub.game.api;

import nl.marijnvanzelst.axon.rummikub.game.model.tile.Tile;

import java.util.List;
import java.util.UUID;

public class TilesDealedEvent {

    private final UUID gameId;

    private final List<Tile> stack;

    private final String player;

    private final List<Tile> playerTiles;

    public TilesDealedEvent(UUID gameId, List<Tile> stack, String player, List<Tile> playerTiles) {
        this.gameId = gameId;
        this.stack = stack;
        this.player = player;
        this.playerTiles = playerTiles;
    }

    public UUID getGameId() {
        return gameId;
    }

    public List<Tile> getStack() {
        return stack;
    }

    public String getPlayer() {
        return player;
    }

    public List<Tile> getPlayerTiles() {
        return playerTiles;
    }
}
