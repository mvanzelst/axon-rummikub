package nl.marijnvanzelst.axon.rummikub.game.model.tile;

import java.util.List;

public class TileGroup implements TileSet {


    @Override
    public boolean canAddTile(Tile tile) {
        return false;
    }

    @Override
    public TileGroup addTile(Tile tile) {
        return null;
    }

    @Override
    public List<Tile> getTiles() {
        return null;
    }
}
