import nl.hanze.hive.*;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpiderMoveSpec {
    @Test
    void spiderCanOnlyMoveOverEmptyFields() throws Hive.IllegalMove {
        Board board = new Board();
        Stone spider = new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER);
        Coord from = new Coord(1, -1);
        Coord to = new Coord(-1, 2);

        board.placeStone(new Coord(-1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        board.placeStone(new Coord(-2, 1), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(-3, 2), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-3, 3), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(-2, 3), new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(-1, 3), new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(0, 2), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        board.placeStone(new Coord(1, 1), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));
        board.placeStone(from, spider);

        board.moveStone(from, to);

        assertEquals(spider, board.get(to).peekStone());
    }

    @Test
    void spiderCanNotMoveLessThanThreeSteps() throws Hive.IllegalMove {
        Board board = new Board();
        Stone spider = new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER);
        Coord from = new Coord(1, -1);
        Coord to = new Coord(2, -1);

        board.placeStone(new Coord(-1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        board.placeStone(new Coord(-2, 1), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(-3, 2), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-3, 3), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(-2, 3), new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(-1, 3), new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(0, 2), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        board.placeStone(new Coord(1, 1), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));
        board.placeStone(from, spider);

        assertThrows(Hive.IllegalMove.class, () -> {
            board.moveStone(from, to);
        });
    }

    @Test
    void spiderCanNotMoveMoreThanThreeSteps() {
        Board board = new Board();
        Stone spider = new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER);
        Coord from = new Coord(1, -1);
        Coord to = new Coord(1, 2);

        board.placeStone(new Coord(-1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        board.placeStone(new Coord(-2, 1), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(-3, 2), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-3, 3), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(-2, 3), new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(-1, 3), new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(0, 2), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        board.placeStone(new Coord(1, 1), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));
        board.placeStone(from, spider);

        assertThrows(Hive.IllegalMove.class, () -> {
            board.moveStone(from, to);
        });
    }

    @Test
    void spiderCantMoveToSpotWhereHeMovesFrom() throws Hive.IllegalMove {
        Board board = new Board();
        Coord from = new Coord(-1, 2);
        Coord to = new Coord(-1, 2);

        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(-1, 1), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));

        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(0, 1), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        board.placeStone(new Coord(-1, 2), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));

        assertThrows(Hive.IllegalMove.class, () -> {
            board.moveStone(from, to);
        });
    }
}
