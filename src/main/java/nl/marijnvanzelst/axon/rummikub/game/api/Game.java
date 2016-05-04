package nl.marijnvanzelst.axon.rummikub.game.api;

import nl.marijnvanzelst.axon.rummikub.game.model.GameState;
import nl.marijnvanzelst.axon.rummikub.game.model.tile.Tile;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.*;

public class Game extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private UUID id;

    private Set<String> players = new HashSet<>();

    private GameState gameState = GameState.NOT_STARTED;

    private List<Tile> stack = new ArrayList<>();

    private Map<String, List<Tile>> playerTiles = new HashMap<>();

    private List<Tile> boardTiles = new ArrayList<>();

    private String playerOnTurn = null;

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
        apply(new GameStartedEvent(this.id, command.getStack()));

        List<Tile> tempStack = new ArrayList<>(this.stack);
        this.players.forEach(player -> {
            List<Tile> playerTiles = new ArrayList<>();
            for (int i = 0; i < 13; i++) {
                playerTiles.add(tempStack.remove(i));
            }
            apply(new TilesDealedEvent(command.getGameId(), new ArrayList<>(tempStack), player, playerTiles));
        });
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
        apply(new TurnSkippedEvent(command.getGameId(), command.getPlayer()));
    }

    @CommandHandler
    public void playTilesCommand(PlayTilesCommand command){
        if(gameState != GameState.STARTED){
            throw new IllegalStateException("Game not started or finished");
        }
        if(!command.getPlayer().equals(playerOnTurn)){
            throw new IllegalStateException("Not player's turn");
        }
        if(stack.isEmpty()){
            throw new IllegalStateException("Cannot skip turn when stack is empty");
        }
        apply(new TurnSkippedEvent(command.getGameId(), command.getPlayer()));
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
        this.stack = event.getStack();
        this.playerOnTurn = this.players.stream().findFirst().get();
    }

    @EventSourcingHandler
    public void on(TilesDealedEvent event) {
        this.stack = event.getStack();
        this.playerTiles.put(event.getPlayer(), event.getPlayerTiles());
    }

    @EventSourcingHandler
    public void on(TurnSkippedEvent event) {
        this.playerTiles.get(event.getPlayer()).add(this.stack.remove(0));
    }
}
