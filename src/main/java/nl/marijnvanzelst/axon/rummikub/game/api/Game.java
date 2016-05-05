package nl.marijnvanzelst.axon.rummikub.game.api;

import nl.marijnvanzelst.axon.rummikub.game.GameEngine;
import nl.marijnvanzelst.axon.rummikub.game.model.Board;
import nl.marijnvanzelst.axon.rummikub.game.model.GameState;
import nl.marijnvanzelst.axon.rummikub.game.model.tile.Tile;
import nl.marijnvanzelst.axon.rummikub.game.model.tile.TileSet;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private UUID id;

    private TreeSet<String> players = new TreeSet<>();

    private GameState gameState = GameState.NOT_STARTED;

    private List<Tile> stack = new ArrayList<>();

    private Board board;

    private String playerOnTurn = null;

    private Set<String> playersPastFirstMove = new HashSet<>();

    private Set<String> playersFinished = new HashSet<>();

    @CommandHandler
    public Game(CreateGameCommand command) {
       apply(new GameCreatedEvent(command.getGameId()));
    }

    @CommandHandler
    public void seatPlayer(SeatPlayerCommand command) {
        if(players.contains(command.getPlayerName())){
            throw new IllegalArgumentException("Player already in game");
        }
        apply(new PlayerSeatedEvent(command.getGameId(), command.getPlayerName()));
    }

    @CommandHandler
    public void startGameCommand(StartGameCommand command) {
        if(gameState != GameState.NOT_STARTED){
            throw new IllegalStateException("Game already started");
        }
        if(players.size() < 2){
            throw new IllegalStateException("Not enough players");
        }
        List<Tile> stack = GameEngine.getShuffledStack();
        Board board = GameEngine.getNewBoard(stack, players);
        apply(new GameStartedEvent(this.id, board, stack, players));
    }

    @CommandHandler
    public void skipTurnCommand(SkipTurnCommand command){
        if(gameState != GameState.STARTED){
            throw new IllegalStateException("Game not started or finished");
        }
        if(!command.getPlayer().equals(playerOnTurn)){
            throw new IllegalStateException("Not player's turn");
        }
        if(stack.isEmpty()){
            throw new IllegalStateException("Cannot skip turn when stack is empty");
        }
        List<Tile> newStack = new ArrayList<>(stack);
        Board newBoard = board.giveTile(command.getPlayer(), newStack.remove(0));
        apply(new TurnSkippedEvent(command.getGameId(), command.getPlayer(), getNextPlayer(), newBoard));
    }

    @CommandHandler
    public void playTilesCommand(PlayTileSetsCommand command){
        if(gameState != GameState.STARTED){
            throw new IllegalStateException("Game not started or finished");
        }
        if(!command.getPlayer().equals(playerOnTurn)){
            throw new IllegalStateException("Not player's turn");
        }
        if(!playersPastFirstMove.contains(command.getPlayer())){
            int tileSum = command.getTileSets().stream()
                    .flatMap(tileSet -> (Stream<Tile>) tileSet.getTiles().stream())
                    .filter(tile -> !tile.isJoker())
                    .mapToInt(tile -> tile.getNumber())
                    .sum();
            if(tileSum < 30){
                throw new IllegalArgumentException("Must play 30 points at first move");
            }
        }
        Board newBoard = board;
        for (TileSet tileSet : command.getTileSets()) {
            newBoard = newBoard.addTileSet(command.getPlayer(), tileSet);
        }
        apply(new TileSetsPlayedEvent(command.getGameId(), command.getPlayer(), command.getTileSets(), getNextPlayer(), newBoard));
        if(newBoard.getTilesByPlayer().get(command.getPlayer()).isEmpty()){
            apply(new PlayerFinishedEvent(command.getGameId(), command.getPlayer()));
        }
        if(getUnfinishedPlayers().size() == 1){
            apply(new GameFinishedEvent(command.getGameId()));
        }
    }

    @EventSourcingHandler
    public void on(GameCreatedEvent event) {
        this.id = event.getGameId();
    }

    @EventSourcingHandler
    public void on(PlayerSeatedEvent event) {
        this.players.add(event.getPlayerName());
    }

    @EventSourcingHandler
    public void on(GameStartedEvent event) {
        this.gameState = GameState.STARTED;
        this.board = event.getBoard();
        this.playerOnTurn = event.getPlayers().stream().findFirst().get();
        this.stack = event.getStack();
    }

    @EventSourcingHandler
    public void on(TurnSkippedEvent event) {
        this.board = event.getNewBoard();
        this.playerOnTurn = event.getNextPlayer();
    }

    @EventSourcingHandler
    public void on(TileSetsPlayedEvent event) {
        this.board = event.getNewBoard();
        this.playerOnTurn = event.getNextPlayer();
    }

    @EventSourcingHandler
    public void on(PlayerFinishedEvent event) {
        this.playersFinished.add(event.getPlayer());
    }

    @EventSourcingHandler
    public void on(GameFinishedEvent event) {
        this.gameState = GameState.FINISHED;
    }

    private List<String> getUnfinishedPlayers(){
        return this.players.stream()
                .filter(player -> !playersFinished.contains(player))
                .collect(Collectors.toList());
    }

    private String getNextPlayer(){
        List<String> unfinishedPlayers = getUnfinishedPlayers();
        if(unfinishedPlayers.isEmpty()){
            throw new IllegalStateException("All players finished");
        }
        int nextPlayerIndex = unfinishedPlayers.indexOf(playerOnTurn) + 1;
        if(unfinishedPlayers.size() - 1 > nextPlayerIndex){
            return unfinishedPlayers.get(nextPlayerIndex);
        } else {
            return unfinishedPlayers.get(0);
        }
    }
}
