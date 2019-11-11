import nl.hanze.hive.IHive;
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
        Tile tile = new Tile(IHive.PlayerColor.BLACK, IHive.TileType.BEETLE);
        Field field = new Field();
        field.addTile(tile);
        assertArrayEquals(new Tile[] {tile}, field.getTiles());
    }

    @Test
    void FieldCanRemoveTile() {
        Tile tile = new Tile(IHive.PlayerColor.BLACK, IHive.TileType.BEETLE);
        Field field = new Field();
        field.addTile(tile);
        field.popTile();
        assertArrayEquals(new Tile[] {}, field.getTiles());
    }

    @Test
    void FieldCanContainTileOnlyOnce() {
        Tile tile = new Tile(IHive.PlayerColor.BLACK, IHive.TileType.BEETLE);
        Field field = new Field();
        field.addTile(tile);
        field.addTile(tile);
        assertArrayEquals(new Tile[] {tile}, field.getTiles());
    }

    @Test
    void FieldHasTileThenTrue() {
        Tile tile = new Tile(IHive.PlayerColor.BLACK, IHive.TileType.BEETLE);
        Field field = new Field();
        field.addTile(tile);
        assertTrue(field.containsTile(tile));
    }
}
