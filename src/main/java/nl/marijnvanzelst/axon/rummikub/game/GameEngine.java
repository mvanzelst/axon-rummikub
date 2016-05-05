package nl.marijnvanzelst.axon.rummikub.game;

import nl.marijnvanzelst.axon.rummikub.game.model.Board;
import nl.marijnvanzelst.axon.rummikub.game.model.tile.Tile;

import java.util.*;

public class GameEngine {

    public static Board getNewBoard(List<Tile> stack, Set<String> players){
        if(players.size() < 2){
            throw new IllegalArgumentException("Not enough players");
        }
        if(players.size() * 13 > stack.size()){
            throw new IllegalArgumentException("Not enough tiles for all players");
        }
        Map<String, List<Tile>> tilesByPlayer = new HashMap<>();
        players.forEach(player -> {
            List<Tile> playerTiles = new ArrayList<>();
            for (int i = 0; i < 13; i++) {
                playerTiles.add(stack.remove(0));
            }
            tilesByPlayer.put(player, playerTiles);
        });
        return new Board(new ArrayList<>(), tilesByPlayer);
    }

    public static List<Tile> getShuffledStack(){
        List<Tile> stack = new ArrayList<>();
        stack.addAll(Arrays.asList(Tile.values()));
        stack.addAll(Arrays.asList(Tile.values()));
        Collections.shuffle(stack);
        return stack;
    }
}
