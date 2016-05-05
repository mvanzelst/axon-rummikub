package nl.marijnvanzelst.axon.rummikub.game.api;

import nl.marijnvanzelst.axon.rummikub.game.api.command.*;
import org.axonframework.commandhandling.gateway.Timeout;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public interface GameCommandGateway {

    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    void createGame(CreateGameCommand command) throws TimeoutException, InterruptedException;

    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    void seatPlayer(SeatPlayerCommand command) throws TimeoutException, InterruptedException;

    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    void startGameCommand(StartGameCommand command) throws TimeoutException, InterruptedException;

    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    void skipTurnCommand(SkipTurnCommand command) throws TimeoutException, InterruptedException;

    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    void playTileSetsCommand(PlayTileSetsCommand command) throws TimeoutException, InterruptedException;


}
