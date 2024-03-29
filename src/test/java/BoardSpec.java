import nl.hanze.hive.Hive;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardSpec {
    @Test
    void BoardShouldOnlyHaveEmptyFieldsWhenCreated() {
        Board board = new Board();
        assertTrue(board.getFields().isEmpty());
    }

    @Test
    void StoneCanBeMovedFromFieldToField() throws Hive.IllegalMove {
        int offset = 10;

        Board board = new Board();
        Coord from = new Coord(offset + 0, offset + 0);
        Coord to = new Coord(offset + 0, offset + 1);

        Stone stone = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        board.placeStone(from, stone);
        board.placeStone(new Coord(offset + 1, offset + 0), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));

        board.moveStone(from, to);
        assertTrue(board.get(to).containsStone(stone));
    }

    @Test
    void StonesCanStack() throws Hive.IllegalMove {
        Board b = new Board();
        Player white = new Player(Hive.Player.WHITE);
        Player black = new Player(Hive.Player.BLACK);

        HiveGame game = new HiveGame(white, black, b);

        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, 0, 1);
        game.play(Hive.Tile.BEETLE, 1, -1);
        game.play(Hive.Tile.SOLDIER_ANT, -1, 2);
        game.move(1, -1, 1, 0);
        game.move(-1, 2, -1, 0);
        game.move(1, 0, 0, 1);

        assertEquals(2, b.get(new Coord(0, 1)).height());
    }

    @Test
    void OnlyMoveTopStoneFromStack() throws Hive.IllegalMove {
        Board board = new Board();
        Coord from = new Coord(0, 0);
        Coord to = new Coord(0, 1);

        Stone blackBeetle = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        Stone whiteBeetle = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);

        board.placeStone(from, blackBeetle);
        board.placeStone(from, whiteBeetle);
        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));

        board.moveStone(from, to);

        Stone[] stones = new Stone[] { blackBeetle };
        assertArrayEquals(stones, board.get(from).getStones());
    }
}
