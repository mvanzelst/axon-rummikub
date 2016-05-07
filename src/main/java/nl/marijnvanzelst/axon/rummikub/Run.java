package nl.marijnvanzelst.axon.rummikub;

import com.google.common.collect.Iterables;
import nl.marijnvanzelst.axon.rummikub.game.GameEngine;
import nl.marijnvanzelst.axon.rummikub.game.api.Game;
import nl.marijnvanzelst.axon.rummikub.game.api.GameCommandGateway;
import nl.marijnvanzelst.axon.rummikub.game.api.command.CreateGameCommand;
import nl.marijnvanzelst.axon.rummikub.game.api.command.SeatPlayerCommand;
import nl.marijnvanzelst.axon.rummikub.game.api.command.SkipTurnCommand;
import nl.marijnvanzelst.axon.rummikub.game.api.command.StartGameCommand;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.gateway.GatewayProxyFactory;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Run {

    public static void main(String[] args){
        SimpleCommandBus commandBus = new SimpleCommandBus();
        EventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("/tmp/events")));
        EventBus eventBus = new SimpleEventBus();
        EventSourcingRepository<Game> repository = new EventSourcingRepository<>(Game.class, eventStore);
        repository.setEventBus(eventBus);
        AggregateAnnotationCommandHandler.subscribe(Game.class, repository, commandBus);
        GatewayProxyFactory gatewayProxyFactory = new GatewayProxyFactory(commandBus);
        GameCommandGateway gameCommandGateway = gatewayProxyFactory.createGateway(GameCommandGateway.class);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() ->
                    playGame(gameCommandGateway, UUID.randomUUID(), Arrays.asList("player1", "player2")));
        }
        executorService.shutdown();
    }

    public static void playGame(GameCommandGateway gameCommandGateway, UUID gameId, List<String> players){
        gameCommandGateway.createGame(new CreateGameCommand(gameId));
        players.forEach(player ->
                gameCommandGateway.seatPlayer(new SeatPlayerCommand(gameId, player)));
        gameCommandGateway.startGameCommand(new StartGameCommand(gameId, GameEngine.getShuffledStack()));

        Iterator<String> playerIterator = Iterables.cycle(players).iterator();
        while(true) {
            gameCommandGateway.skipTurnCommand(new SkipTurnCommand(gameId, playerIterator.next()));
        }
    }
}
