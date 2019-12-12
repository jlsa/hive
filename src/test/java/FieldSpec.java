import nl.hanze.hive.Hive;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// should be reworked into board spec
public class FieldSpec {
    // 2b
    @Test
    void FieldHasSixNeighbors() {
        Field field = new Field();
        assertEquals(6, field.getNeighbors().length, "Field has six neighbors");
    }

    @Test
    void FieldCanAddTile() {
        Stone stone = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        Field field = new Field();
        field.addStone(stone);
        assertArrayEquals(new Stone[] {stone}, field.getStones());
    }

    @Test
    void FieldCanRemoveTile() {
        Stone stone = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        Field field = new Field();
        field.addStone(stone);
        field.popStone();
        assertArrayEquals(new Stone[] {}, field.getStones());
    }

    @Test
    void FieldCanContainTileOnlyOnce() {
        Stone stone = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        Field field = new Field();
        field.addStone(stone);
        field.addStone(stone);
        assertArrayEquals(new Stone[] {stone}, field.getStones());
    }

    @Test
    void FieldHasTileThenTrue() {
        Stone stone = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        Field field = new Field();
        field.addStone(stone);
        assertTrue(field.containsStone(stone));
    }
}
