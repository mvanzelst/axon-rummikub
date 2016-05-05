package nl.marijnvanzelst.axon.rummikub.game.model.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TileRun implements TileSet<TileRun> {

    private final List<Tile> tiles;

    public TileRun(){
        this(new ArrayList<>());
    }

    private TileRun(List<Tile> tiles){
        this.tiles = tiles;
    }

    public static TileRun build(List<Tile> tiles) {
        TileRun tileRun = new TileRun();
        for (Tile tile : tiles) {
            tileRun = tileRun.addTile(tile);
        }
        return tileRun;
    }

    @Override
    public boolean canAddTile(Tile tile) {
        if(tiles.isEmpty() || tile.isJoker()){
            return true;
        }
        Optional<Integer> lastNonJokerPosition = getLastNonJokerPosition();
        if(!lastNonJokerPosition.isPresent()){
            return true;
        } else {
            Tile previousTile = tiles.get(lastNonJokerPosition.get());
            if(previousTile.getColor() != tile.getColor()){
                return false;
            } else {
                int positionDifference = tiles.size() - lastNonJokerPosition.get();
                return previousTile.getNumber() + positionDifference == tile.getNumber();
            }
        }
    }

    private Optional<Integer> getLastNonJokerPosition(){
        for (int i = tiles.size() - 1; i >= 0; i--) {
            if(!tiles.get(i).isJoker()){
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    @Override
    public TileRun addTile(Tile tile) {
        if(!canAddTile(tile)){
            throw new IllegalArgumentException("Invalid tile");
        } else {
            List<Tile> newTiles = new ArrayList<>(tiles);
            newTiles.add(tile);
            return new TileRun(newTiles);
        }
    }

    @Override
    public List<Tile> getTiles() {
        return new ArrayList<>(tiles);
    }
}
