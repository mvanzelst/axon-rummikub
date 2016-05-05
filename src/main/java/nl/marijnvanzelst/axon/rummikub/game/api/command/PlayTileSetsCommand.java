package nl.marijnvanzelst.axon.rummikub.game.api.command;

import nl.marijnvanzelst.axon.rummikub.game.model.tile.TileSet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayTileSetsCommand {

    private final UUID gameId;

    private final String player;

    private final List<TileSet> tileSets;

    public PlayTileSetsCommand(UUID gameId, String player, List<TileSet> tileSets) {
        this.gameId = gameId;
        this.player = player;
        this.tileSets = new ArrayList<>(tileSets);
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
}
