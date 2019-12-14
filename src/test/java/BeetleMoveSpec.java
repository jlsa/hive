import nl.josaho.stones.*;
import nl.hanze.hive.*;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// 5e + 7a
public class BeetleMoveSpec {
    @Test
    void beetleMoveDown() {
        Board board = new Board();

        Coord from = new Coord(0, 0);
        Coord to = new Coord(-1, 1);

        board.placeStone(from, new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(from, new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        board.placeStone(new Coord(0, 1), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));

        assertTrue(Move.isValidMove(board, from, to));
    }

    @Test
    void beetleMoveSameLevel() {
        Board board = new Board();

        Coord from = new Coord(0, 0);
        Coord to = new Coord(-1, 1);

        board.placeStone(from, new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        board.placeStone(new Coord(0, 1), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));

        assertTrue(Move.isValidMove(board, from, to));
    }

    @Test
    void beetleMoveUp() {
        Board board = new Board();

        Coord from = new Coord(0, 0);
        Coord to = new Coord(-1, 1);

        board.placeStone(from, new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        board.placeStone(to, new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(0, 1), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));

        assertTrue(Move.isValidMove(board, from, to));
    }
}
