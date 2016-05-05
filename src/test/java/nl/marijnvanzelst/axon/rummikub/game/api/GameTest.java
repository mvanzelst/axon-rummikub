package nl.marijnvanzelst.axon.rummikub.game.api;

import nl.marijnvanzelst.axon.rummikub.game.GameEngine;
import nl.marijnvanzelst.axon.rummikub.game.model.Board;
import nl.marijnvanzelst.axon.rummikub.game.model.tile.Tile;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class GameTest {

    private FixtureConfiguration<Game> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(Game.class)
                .registerAnnotatedCommandHandler(Game.class);
    }

    @Test
    public void testStartGameCommand() throws Exception {
        List<Tile> stack = GameEngine.getShuffledStack();
        List<String> playerList = Arrays.asList("player1", "player2");
        TreeSet<String> players = new TreeSet<>(playerList);
        UUID gameId = UUID.randomUUID();

        fixture.given(new GameCreatedEvent(gameId),
                      new PlayerSeatedEvent(gameId, playerList.get(0)),
                      new PlayerSeatedEvent(gameId, playerList.get(1)))
                .when(new StartGameCommand(gameId, stack))
                .expectPublishedEvents(new GameStartedEvent(
                        gameId,
                        new Board(new ArrayList<>(), new HashMap<String, List<Tile>>(){{
                            put(playerList.get(0), stack.subList(0, 13));
                            put(playerList.get(1), stack.subList(13, 26));
                        }}),
                        stack.subList(26, stack.size()),
                        players,
                        players.first()));
    }
}