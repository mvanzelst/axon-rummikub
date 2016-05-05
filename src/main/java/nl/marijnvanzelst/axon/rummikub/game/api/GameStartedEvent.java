package nl.marijnvanzelst.axon.rummikub.game.api;

import nl.marijnvanzelst.axon.rummikub.game.model.Board;
import nl.marijnvanzelst.axon.rummikub.game.model.tile.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;

public class GameStartedEvent {

    private final UUID gameId;
    private final Board board;
    private final List<Tile> stack;
    private TreeSet<String> players;

    public GameStartedEvent(UUID gameId, Board board, List<Tile> stack, TreeSet<String> players) {
        this.gameId = gameId;
        this.board = board;
        this.stack = new ArrayList<>(stack);
        this.players = new TreeSet<>(players);
    }

    public UUID getGameId() {
        return gameId;
    }

    public Board getBoard() {
        return board;
    }

    public List<Tile> getStack() {
        return new ArrayList<>(stack);
    }

    public TreeSet<String> getPlayers() {
        return new TreeSet<>(players);
    }
}
