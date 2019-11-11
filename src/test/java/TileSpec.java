import nl.hanze.hive.IHive.*;
import nl.josaho.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileSpec {

    @Test
    void TileCanOnlyBeInOneField() {
        Tile tile = new Tile(PlayerColor.BLACK);
    }

    @Test
    void givenBlackTileThenEquals() {
        Tile tile = new Tile(PlayerColor.BLACK);
        assertEquals(PlayerColor.BLACK, tile.getColor());
    }

    @Test
    void givenWhiteTileThenEquals() {
        Tile tile = new Tile(PlayerColor.WHITE);
        assertEquals(PlayerColor.WHITE, tile.getColor());
    }

    @Test
    void givenEmptyEdgesArrayThenTrue() {
        Tile tile = new Tile(PlayerColor.BLACK);
        assertArrayEquals(tile.getEdges(), new Tile[6]);
    }

    @Test
    void givenQueenTileWhenGetTileTypeThenEqual() {
        Tile tile = new Tile(PlayerColor.BLACK, TileType.QUEEN_BEE);
        assertEquals(TileType.QUEEN_BEE, tile.getTileType());
    }

    @Test
    void givenSpiderTileWhenGetTileTypeThenEqual() {
        Tile tile = new Tile(PlayerColor.BLACK, TileType.SPIDER);
        assertEquals(TileType.SPIDER, tile.getTileType());
    }

    @Test
    void givenBeetleTileWhenGetTileTypeThenEqual() {
        Tile tile = new Tile(PlayerColor.BLACK, TileType.BEETLE);
        assertEquals(TileType.BEETLE, tile.getTileType());
    }

    @Test
    void givenGrashopperTileWhenGetTileTypeThenEqual() {
        Tile tile = new Tile(PlayerColor.BLACK, TileType.GRASSHOPPER);
        assertEquals(TileType.GRASSHOPPER, tile.getTileType());
    }

    @Test
    void givenSoldierAntTileWhenGetTileTypeThenEqual() {
        Tile tile = new Tile(PlayerColor.BLACK, TileType.SOLDIER_ANT);
        assertEquals(TileType.SOLDIER_ANT, tile.getTileType());
    }
}
