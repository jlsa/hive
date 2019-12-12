import nl.hanze.hive.Hive;
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

        Stone stone = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        boolean canAddFirst = board.placeTile(positionOne, stone);
        boolean canAddSecond = board.placeTile(positionTwo, stone);
        assertNotEquals(canAddFirst, canAddSecond);
    }

    @Test
    void TileCanBeMovedFromFieldToField() {
        Board board = new Board();
        Coord from = new Coord(0, 0);
        Coord to = new Coord(0, 1);
        board.addField(from, new Field());
        board.addField(to, new Field());

        Stone stone = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        board.placeTile(from, stone);

        board.moveTile(from, to);
        assertTrue(board.fields.get(to).containsStone(stone));
    }

    @Test
    void TilesCanStack() {
        // need to edit field.popTile as it should be able to stack tiles.
        Board board = new Board();
        Coord coord = new Coord(0, 0);
        board.addField(coord, new Field());

        Stone beetle = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        Stone spider = new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER);
        Stone ant = new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER);

        board.placeTile(coord, beetle);
        board.placeTile(coord, spider);
        board.placeTile(coord, ant);

        Stone[] stones = new Stone[3];
        stones[0] = beetle;
        stones[1] = spider;
        stones[2] = ant;

        assertArrayEquals(stones, board.fields.get(coord).getStones());
    }

    @Test
    void OnlyMoveTopTileFromStack() {
        Board board = new Board();
        Coord from = new Coord(0, 0);
        Coord to = new Coord(0, 1);
        board.addField(from, new Field());
        board.addField(to, new Field());

        Stone blackBeetle = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        board.placeTile(from, blackBeetle);
        Stone whiteBeetle = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.placeTile(from, whiteBeetle);

        board.moveTile(from, to);
        Stone[] stones = new Stone[] {blackBeetle};
        assertArrayEquals(stones, board.fields.get(from).getStones());
    }
}
