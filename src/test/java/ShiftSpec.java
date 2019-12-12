import nl.josaho.stones.Move;
import nl.hanze.hive.*;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ShiftSpec {
    // 6a
    @Test
    void StoneMovesByShifting() {
        Board board = new Board();
        Stone queenBeeStone = new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);

        board.placeTile(new Coord(0, 0), queenBeeStone);

        board.shiftStone(new Coord(0, 0), new Coord(0, 1));

        Field f = board.get(new Coord(0, 1));
        Stone expectedTile = new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);

        assertEquals(expectedTile, f.peekStone());
    }

    // 6b
    @Test
    void shiftIsNotAllowed() {
        Board board = new Board();
        Stone stone = new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);
        Coord from = new Coord(0, 0);
        Coord to = new Coord(1, -1);

        board.placeTile(from, stone);
        board.placeTile(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeTile(new Coord(1, 0), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        assertFalse(Move.isValidMove(board, from, to));
    }
}
