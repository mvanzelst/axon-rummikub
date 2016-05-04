package nl.marijnvanzelst.axon.rummikub.game.model;

import nl.marijnvanzelst.axon.rummikub.game.model.tile.TileGroup;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<TileGroup> tileGroups;

    public Board(List<TileGroup> tileGroups) {
        this.tileGroups = new ArrayList<>(tileGroups);
    }

    public List<TileGroup> getTileGroups() {
        return new ArrayList<>(tileGroups);
    }

    public Board addTileGroup(TileGroup tileGroup){
        return null;
    }
}
