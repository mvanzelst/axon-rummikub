package nl.marijnvanzelst.axon.rummikub.game.model.tile;

import java.util.List;

public interface TileSet {

    boolean canAddTile(Tile tile);

    TileGroup addTile(Tile tile);

    List<Tile> getTiles();
}
