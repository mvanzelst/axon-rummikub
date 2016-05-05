package nl.marijnvanzelst.axon.rummikub.game.model.tile;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TileRunTest {

    @Test
    public void threeTileRun() {
        TileRun tileRun = new TileRun()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.TWO_BLACK)
                .addTile(Tile.THREE_BLACK);
        assertTrue(tileRun.isComplete());
    }

    @Test
    public void thirteenTileRun() {
        TileRun tileRun = new TileRun()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.TWO_BLACK)
                .addTile(Tile.THREE_BLACK)
                .addTile(Tile.FOUR_BLACK)
                .addTile(Tile.FIVE_BLACK)
                .addTile(Tile.SIX_BLACK)
                .addTile(Tile.SEVEN_BLACK)
                .addTile(Tile.EIGHT_BLACK)
                .addTile(Tile.NINE_BLACK)
                .addTile(Tile.TEN_BLACK)
                .addTile(Tile.ELEVEN_BLACK)
                .addTile(Tile.TWELVE_BLACK)
                .addTile(Tile.THIRTEEN_BLACK);
        assertTrue(tileRun.isComplete());
    }

    @Test
    public void thirteenTileRunTwoJokers() {
        TileRun tileRun = new TileRun()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.TWO_BLACK)
                .addTile(Tile.THREE_BLACK)
                .addTile(Tile.JOKER)
                .addTile(Tile.JOKER)
                .addTile(Tile.SIX_BLACK)
                .addTile(Tile.SEVEN_BLACK)
                .addTile(Tile.JOKER)
                .addTile(Tile.JOKER)
                .addTile(Tile.TEN_BLACK)
                .addTile(Tile.ELEVEN_BLACK)
                .addTile(Tile.TWELVE_BLACK)
                .addTile(Tile.THIRTEEN_BLACK);
        assertTrue(tileRun.isComplete());
    }

    @Test
    public void threeTileRunWithJokerFirst() {
        TileRun tileRun = new TileRun()
                .addTile(Tile.JOKER)
                .addTile(Tile.TWO_BLACK)
                .addTile(Tile.THREE_BLACK);
        assertTrue(tileRun.isComplete());
    }

    @Test
    public void threeTileRunWithJokerMiddle() {
        TileRun tileRun = new TileRun()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.JOKER)
                .addTile(Tile.THREE_BLACK);
        assertTrue(tileRun.isComplete());
    }

    @Test
    public void threeTileRunWithJokerLast() {
        TileRun tileRun = new TileRun()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.JOKER)
                .addTile(Tile.THREE_BLACK);
        assertTrue(tileRun.isComplete());
    }

    @Test
    public void threeTileRunWithTwoJokersFirst() {
        TileRun tileRun = new TileRun()
                .addTile(Tile.JOKER)
                .addTile(Tile.JOKER)
                .addTile(Tile.THREE_BLACK);
        assertTrue(tileRun.isComplete());
    }

    @Test
    public void threeTileRunWithTwoJokersLast() {
        TileRun tileRun = new TileRun()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.JOKER)
                .addTile(Tile.JOKER);
        assertTrue(tileRun.isComplete());
    }

    @Test
    public void fourTileRunWithFourJokersMiddle() {
        TileRun tileRun = new TileRun()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.JOKER)
                .addTile(Tile.JOKER)
                .addTile(Tile.FOUR_BLACK);
        assertTrue(tileRun.isComplete());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidSecondTileRun() {
        new TileRun()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.THREE_BLACK);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sameNumber() {
        new TileRun()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.ONE_BLACK);
    }

    @Test(expected = IllegalArgumentException.class)
    public void differentColor() {
        new TileRun()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.TWO_BLACK)
                .addTile(Tile.THREE_BLUE);
    }

    @Test
    public void incompleteRunOfTwo() {
        TileRun tileRun = new TileRun()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.TWO_BLACK);
        assertFalse(tileRun.isComplete());
    }

    @Test
    public void incompleteRunOfOne() {
        TileRun tileRun = new TileRun()
                .addTile(Tile.ONE_BLACK);
        assertFalse(tileRun.isComplete());
    }
}