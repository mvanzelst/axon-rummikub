package nl.marijnvanzelst.axon.rummikub.game.api;

import nl.marijnvanzelst.axon.rummikub.game.api.command.*;
import org.axonframework.commandhandling.gateway.Timeout;

import java.util.concurrent.TimeUnit;

public interface GameCommandGateway {

    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    void createGame(CreateGameCommand command);

    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    void seatPlayer(SeatPlayerCommand command);

    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    void startGameCommand(StartGameCommand command);

    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    void skipTurnCommand(SkipTurnCommand command);

    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    void playTileSetsCommand(PlayTileSetsCommand command);


}
