package nl.marijnvanzelst.axon.rummikub.game;

import nl.marijnvanzelst.axon.rummikub.game.model.tile.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameEngine {

    public static List<Tile> getShuffledStack(){
        List<Tile> stack = new ArrayList<>();
        stack.addAll(Arrays.asList(Tile.values()));
        stack.addAll(Arrays.asList(Tile.values()));
        Collections.shuffle(stack);
        return stack;
    }
}
