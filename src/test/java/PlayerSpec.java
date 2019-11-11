import nl.hanze.hive.IHive.*;
import nl.josaho.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerSpec {

    // 1C
    @Test
    void givenANewBlackPlayerHasAllStartTiles() {
        Player player = new Player(PlayerColor.BLACK);

        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(PlayerColor.BLACK, TileType.QUEEN_BEE));
        tiles.add(new Tile(PlayerColor.BLACK, TileType.SPIDER));
        tiles.add(new Tile(PlayerColor.BLACK, TileType.SPIDER));
        tiles.add(new Tile(PlayerColor.BLACK, TileType.BEETLE));
        tiles.add(new Tile(PlayerColor.BLACK, TileType.BEETLE));
        tiles.add(new Tile(PlayerColor.BLACK, TileType.SOLDIER_ANT));
        tiles.add(new Tile(PlayerColor.BLACK, TileType.SOLDIER_ANT));
        tiles.add(new Tile(PlayerColor.BLACK, TileType.SOLDIER_ANT));
        tiles.add(new Tile(PlayerColor.BLACK, TileType.GRASSHOPPER));
        tiles.add(new Tile(PlayerColor.BLACK, TileType.GRASSHOPPER));
        tiles.add(new Tile(PlayerColor.BLACK, TileType.GRASSHOPPER));
        assertIterableEquals(player.getTiles(), tiles);
    }

    @Test
    void givenANewWhitePlayerHasAllStartTiles() {
        Player player = new Player(PlayerColor.WHITE);
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(PlayerColor.WHITE, TileType.QUEEN_BEE));
        tiles.add(new Tile(PlayerColor.WHITE, TileType.SPIDER));
        tiles.add(new Tile(PlayerColor.WHITE, TileType.SPIDER));
        tiles.add(new Tile(PlayerColor.WHITE, TileType.BEETLE));
        tiles.add(new Tile(PlayerColor.WHITE, TileType.BEETLE));
        tiles.add(new Tile(PlayerColor.WHITE, TileType.SOLDIER_ANT));
        tiles.add(new Tile(PlayerColor.WHITE, TileType.SOLDIER_ANT));
        tiles.add(new Tile(PlayerColor.WHITE, TileType.SOLDIER_ANT));
        tiles.add(new Tile(PlayerColor.WHITE, TileType.GRASSHOPPER));
        tiles.add(new Tile(PlayerColor.WHITE, TileType.GRASSHOPPER));
        tiles.add(new Tile(PlayerColor.WHITE, TileType.GRASSHOPPER));
        assertIterableEquals(player.getTiles(), tiles);
    }
}
