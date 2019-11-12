import nl.hanze.hive.Hive.*;
import nl.josaho.Stone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoneSpec {

    @Test
    void TileCanOnlyBeInOneField() {
        Stone stone = new Stone(Player.BLACK);
    }

    @Test
    void givenBlackTileThenEquals() {
        Stone stone = new Stone(Player.BLACK);
        assertEquals(Player.BLACK, stone.getColor());
    }

    @Test
    void givenWhiteTileThenEquals() {
        Stone stone = new Stone(Player.WHITE);
        assertEquals(Player.WHITE, stone.getColor());
    }

    @Test
    void givenEmptyEdgesArrayThenTrue() {
        Stone stone = new Stone(Player.BLACK);
        assertArrayEquals(stone.getEdges(), new Stone[6]);
    }

    @Test
    void givenQueenTileWhenGetTileThenEqual() {
        Stone stone = new Stone(Player.BLACK, Tile.QUEEN_BEE);
        assertEquals(Tile.QUEEN_BEE, stone.getTileType());
    }

    @Test
    void givenSpiderTileWhenGetTileThenEqual() {
        Stone stone = new Stone(Player.BLACK, Tile.SPIDER);
        assertEquals(Tile.SPIDER, stone.getTileType());
    }

    @Test
    void givenBeetleTileWhenGetTileThenEqual() {
        Stone stone = new Stone(Player.BLACK, Tile.BEETLE);
        assertEquals(Tile.BEETLE, stone.getTileType());
    }

    @Test
    void givenGrashopperTileWhenGetTileThenEqual() {
        Stone stone = new Stone(Player.BLACK, Tile.GRASSHOPPER);
        assertEquals(Tile.GRASSHOPPER, stone.getTileType());
    }

    @Test
    void givenSoldierAntTileWhenGetTileThenEqual() {
        Stone stone = new Stone(Player.BLACK, Tile.SOLDIER_ANT);
        assertEquals(Tile.SOLDIER_ANT, stone.getTileType());
    }
}
