package nl.marijnvanzelst.axon.rummikub.game.model.tile;

import java.util.List;

public interface TileSet<T extends TileSet> {

    boolean canAddTile(Tile tile);

    T addTile(Tile tile);

    List<Tile> getTiles();

    default boolean isComplete(){
        return getTiles().size() >= 3;
    }

}
