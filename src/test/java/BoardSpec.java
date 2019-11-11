import nl.hanze.hive.IHive;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardSpec {
    // 2a = moeilijk.
    // hier zou 2a moeten komen.
    // board.getField(coord) for infinite board test
    // remember there can't be empty spots between. they always need to be connected.

    @Test
    void BoardShouldOnlyHaveEmptyFieldsWhenCreated() {
        Board board = new Board();
        assertTrue(board.getFields().isEmpty());
    }

    // same(===) is not equals
    @Test
    void TileCanOnlyBeInOneField() {
        Board board = new Board();
        Coord positionOne = new Coord(2, 1);
        Coord positionTwo = new Coord(3, 1);
        board.addField(positionOne, new Field());
        board.addField(positionTwo, new Field());

        Tile tile = new Tile(IHive.PlayerColor.BLACK, IHive.TileType.BEETLE);
        boolean canAddFirst = board.placeTile(positionOne, tile);
        boolean canAddSecond = board.placeTile(positionTwo, tile);
        assertNotEquals(canAddFirst, canAddSecond);
    }

    @Test
    void TileCanBeMovedFromFieldToField() {
        Board board = new Board();
        Coord from = new Coord(0, 0);
        Coord to = new Coord(0, 1);
        board.addField(from, new Field());
        board.addField(to, new Field());

        Tile tile = new Tile(IHive.PlayerColor.BLACK, IHive.TileType.BEETLE);
        board.placeTile(from, tile);

        board.moveTile(from, to);
        assertTrue(board.fields.get(to).containsTile(tile));
    }

    @Test
    void TilesCanStack() {
        // need to edit field.popTile as it should be able to stack tiles.
        Board board = new Board();
        Coord coord = new Coord(0, 0);
        board.addField(coord, new Field());

        Tile beetle = new Tile(IHive.PlayerColor.BLACK, IHive.TileType.BEETLE);
        Tile spider = new Tile(IHive.PlayerColor.WHITE, IHive.TileType.SPIDER);
        Tile ant = new Tile(IHive.PlayerColor.BLACK, IHive.TileType.GRASSHOPPER);

        board.placeTile(coord, beetle);
        board.placeTile(coord, spider);
        board.placeTile(coord, ant);

        Tile[] tiles = new Tile[3];
        tiles[0] = beetle;
        tiles[1] = spider;
        tiles[2] = ant;

        assertArrayEquals(tiles, board.fields.get(coord).getTiles());
    }

    @Test
    void OnlyMoveTopTileFromStack() {
        Board board = new Board();
        Coord from = new Coord(0, 0);
        Coord to = new Coord(0, 1);
        board.addField(from, new Field());
        board.addField(to, new Field());

        Tile blackBeetle = new Tile(IHive.PlayerColor.BLACK, IHive.TileType.BEETLE);
        board.placeTile(from, blackBeetle);
        Tile whiteBeetle = new Tile(IHive.PlayerColor.WHITE, IHive.TileType.BEETLE);
        board.placeTile(from, whiteBeetle);

        board.moveTile(from, to);
        Tile[] tiles = new Tile[] {blackBeetle};
        assertArrayEquals(tiles, board.fields.get(from).getTiles());
    }
}
