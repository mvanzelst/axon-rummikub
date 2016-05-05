package nl.marijnvanzelst.axon.rummikub.game.model.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TileGroup implements TileSet<TileGroup> {

    private final List<Tile> tiles;

    private final Optional<Integer> groupNumber;

    public TileGroup() {
        tiles = new ArrayList<>();
        groupNumber = Optional.empty();
    }

    private TileGroup(List<Tile> tiles, Optional<Integer> groupNumber) {
        this.tiles = tiles;
        this.groupNumber = groupNumber;
    }

    public static TileGroup build(List<Tile> tiles) {
        TileGroup tileGroup = new TileGroup();
        for (Tile tile : tiles) {
            tileGroup = tileGroup.addTile(tile);
        }
        return tileGroup;
    }

    @Override
    public boolean canAddTile(Tile tile) {
        if(tile.isJoker()) {
            return true;
        } else if(!groupNumber.isPresent()) {
            return true;
        } else if(tile.getNumber().equals(groupNumber.get()) && !this.findNonJokers().contains(tile)) {
            return true;
        } else {
            return false;
        }
    }

    private List<Tile> findNonJokers(){
        return tiles.stream()
                .filter(tile -> !tile.isJoker())
                .collect(Collectors.toList());
    }

    @Override
    public TileGroup addTile(Tile tile) {
        if(!canAddTile(tile)){
            throw new IllegalArgumentException("Invalid tile");
        } else {
            List<Tile> newTiles = new ArrayList<>(tiles);
            newTiles.add(tile);

            Optional<Integer> newGroupNumber;
            if(!groupNumber.isPresent()){
                newGroupNumber = tile.isJoker() ? Optional.empty() : Optional.of(tile.getNumber());
            } else {
                newGroupNumber = groupNumber;
            }

            return new TileGroup(newTiles, newGroupNumber);
        }
    }

    @Override
    public List<Tile> getTiles() {
        return new ArrayList<>(tiles);
    }
}
