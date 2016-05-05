package nl.marijnvanzelst.axon.rummikub.game.model.tile;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TileGroupTest {

    @Test
    public void testThreeTileGroup(){
        TileGroup tileGroup = new TileGroup()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.ONE_BLUE)
                .addTile(Tile.ONE_RED);
        assertTrue(tileGroup.isComplete());
    }

    @Test
    public void testThreeTileGroupOneJoker(){
        TileGroup tileGroup = new TileGroup()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.ONE_BLUE)
                .addTile(Tile.JOKER);
        assertTrue(tileGroup.isComplete());
    }

    @Test
    public void testFourTileGroup(){
        TileGroup tileGroup = new TileGroup()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.ONE_BLUE)
                .addTile(Tile.ONE_RED)
                .addTile(Tile.ONE_YELLOW);
        assertTrue(tileGroup.isComplete());
    }

    @Test
    public void testFourTileGroupOneJoker(){
        TileGroup tileGroup = new TileGroup()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.ONE_BLUE)
                .addTile(Tile.ONE_RED)
                .addTile(Tile.JOKER);
        assertTrue(tileGroup.isComplete());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDuplicate(){
        new TileGroup()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.ONE_BLUE)
                .addTile(Tile.ONE_RED)
                .addTile(Tile.ONE_BLACK);
    }

    @Test
    public void testIncomplete(){
        TileGroup tileGroup = new TileGroup()
                .addTile(Tile.ONE_BLACK)
                .addTile(Tile.ONE_BLUE);
        assertFalse(tileGroup.isComplete());
    }
}