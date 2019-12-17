import nl.josaho.movements.*;
import nl.hanze.hive.*;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class QueenMoveSpec {
    @Test
    void queenBeeMoveUpFails() {
        Board board = new Board();

        Coord from = new Coord(0, 0);
        Coord to = new Coord(-1, 1);

        board.placeStone(from, new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(to, new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));

        assertFalse(Move.isValidMove(board, from, to));
    }

    @Test
    void queenBeeMoveSameLevel() {
        Board board = new Board();

        Coord from = new Coord(0, 0);
        Coord to = new Coord(-1, 1);

        board.placeStone(from, new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(0, 1), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));

        assertTrue(Move.isValidMove(board, from, to));
    }
}
