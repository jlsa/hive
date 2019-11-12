import nl.hanze.hive.Hive;
import nl.josaho.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerSpec {

    // 1C
    @Test
    void givenANewBlackPlayerHasAllStartTiles() {
        nl.josaho.Player player = new Player(Hive.Player.BLACK);

        ArrayList<Stone> stones = new ArrayList<>();
        stones.add(new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        stones.add(new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));
        stones.add(new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));
        stones.add(new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        stones.add(new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        stones.add(new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        stones.add(new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        stones.add(new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        stones.add(new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        stones.add(new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        stones.add(new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        assertIterableEquals(player.getStones(), stones);
    }

    @Test
    void givenANewWhitePlayerHasAllStartTiles() {
        Player player = new Player(Hive.Player.WHITE);
        ArrayList<Stone> stones = new ArrayList<>();
        stones.add(new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        stones.add(new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));
        stones.add(new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));
        stones.add(new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        stones.add(new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        stones.add(new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
        stones.add(new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
        stones.add(new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
        stones.add(new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        stones.add(new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        stones.add(new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        assertIterableEquals(player.getStones(), stones);
    }
}
