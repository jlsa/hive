import nl.hanze.hive.Hive;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class FieldSpec {
    // 2b
    @Test
    void FieldHasSixNeighbors() {
        Field field = new Field();
        assertEquals(6, field.getNeighbors().length);
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
    void FieldHasTileThenTrue() {
        Stone stone = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        Field field = new Field();
        field.addStone(stone);
        assertTrue(field.containsStone(stone));
    }
}
