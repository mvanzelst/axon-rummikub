package nl.marijnvanzelst.axon.rummikub;

import nl.marijnvanzelst.axon.rummikub.game.GameEngine;
import nl.marijnvanzelst.axon.rummikub.game.api.Game;
import nl.marijnvanzelst.axon.rummikub.game.api.GameCommandGateway;
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
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Run {

    private final static UUID gameId = UUID.fromString("5eb1e249-d369-4f28-92fe-f6a2204ba04d");

    public static void main(String[] args) throws TimeoutException, InterruptedException {
        SimpleCommandBus commandBus = new SimpleCommandBus();
        EventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("/tmp/events")));
        EventBus eventBus = new SimpleEventBus();
        EventSourcingRepository<Game> repository = new EventSourcingRepository<>(Game.class, eventStore);
        repository.setEventBus(eventBus);
        AggregateAnnotationCommandHandler.subscribe(Game.class, repository, commandBus);
        GatewayProxyFactory gatewayProxyFactory = new GatewayProxyFactory(commandBus);
        GameCommandGateway gameCommandGateway = gatewayProxyFactory.createGateway(GameCommandGateway.class);
        //gameCommandGateway.createGame(new CreateGameCommand(gameId));
        gameCommandGateway.startGameCommand(new StartGameCommand(gameId, GameEngine.getShuffledStack()));
    }
}
