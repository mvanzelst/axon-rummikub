package nl.marijnvanzelst.axon.rummikub.game.model;

import nl.marijnvanzelst.axon.rummikub.game.model.tile.Tile;
import nl.marijnvanzelst.axon.rummikub.game.model.tile.TileSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final List<TileSet> boardSets;

    private final Map<String, List<Tile>> tilesByPlayer;

    public Board(List<TileSet> boardSets, Map<String, List<Tile>> tilesByPlayer) {
        this.boardSets = new ArrayList<>(boardSets);
        this.tilesByPlayer = new HashMap<>(tilesByPlayer);
    }

    public Board addTileSet(String player, TileSet tileSet){
        verifyAddTileSet(player, tileSet);

        Map<String, List<Tile>> newTilesByPlayer = new HashMap<>(tilesByPlayer);
        List<Tile> newPlayerTiles = newTilesByPlayer.get(player);
        tileSet.getTiles().forEach(tile -> newPlayerTiles.remove(tile));

        List<TileSet> newBoardSets = new ArrayList<>(boardSets);
        newBoardSets.add(tileSet);

        return new Board(newBoardSets, newTilesByPlayer);
    }

    private void verifyAddTileSet(String player, TileSet tileSet){
        if(!tileSet.isComplete()){
            throw new IllegalArgumentException("Incomplete tileset");
        }
        if(!tilesByPlayer.containsKey(player)){
            throw new IllegalArgumentException("Unknown player");
        }
        List<Tile> playerTiles = tilesByPlayer.get(tilesByPlayer);
        if(!playerTiles.containsAll(tileSet.getTiles())){
            throw new IllegalStateException("Player is not in possession of all tiles");
        }
    }

    public List<TileSet> getBoardSets() {
        return new ArrayList<>(boardSets);
    }

    public Map<String, List<Tile>> getTilesByPlayer() {
        return new HashMap<>(tilesByPlayer);
    }

    public Board giveTile(String player, Tile tile) {
        if(!tilesByPlayer.containsKey(player)){
            throw new IllegalArgumentException("Unknown player");
        }
        Map<String, List<Tile>> newTilesByPlayer = new HashMap<>(tilesByPlayer);
        newTilesByPlayer.get(player).add(tile);
        return new Board(new ArrayList<>(boardSets), newTilesByPlayer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (!boardSets.equals(board.boardSets)) return false;
        return tilesByPlayer.equals(board.tilesByPlayer);

    }

    @Override
    public int hashCode() {
        int result = boardSets.hashCode();
        result = 31 * result + tilesByPlayer.hashCode();
        return result;
    }
}
