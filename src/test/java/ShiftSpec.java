import nl.josaho.movements.Move;
import nl.hanze.hive.*;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ShiftSpec {
    // 6a
    @Test
    void stoneMovesByShifting() throws Hive.IllegalMove {
        Board board = new Board();
        Stone queenBeeStone = new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);

        board.placeStone(new Coord(0, 0), queenBeeStone);
        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));


        board.moveStone(new Coord(0, 0), new Coord(0, 1));

        Field f = board.get(new Coord(0, 1));
        Stone expectedTile = new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);

        assertEquals(expectedTile, f.peekStone());
    }

    // 6b
    @Test
    void shiftIsNotAllowed() throws Hive.IllegalMove {
        Board board = new Board();
        Stone stone = new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);
        Coord from = new Coord(0, 0);
        Coord to = new Coord(1, -1);

        board.placeStone(from, stone);
        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));

        assertThrows(Hive.IllegalMove.class, () -> {
            board.moveStone(from, to);
        });
    }

    // 6c
    @Test
    void stoneHasToBeConnectedWhileShifting() throws Hive.IllegalMove {
        Board board = new Board();
        Coord from = new Coord(-1, 1);
        Coord to = new Coord(0, 1);

        // -1 +1
        board.placeStone(new Coord(-1, 1), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        // -1  0
        board.placeStone(new Coord(-1, 0), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        //  0 -1
        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        // +1 -1
        board.placeStone(new Coord(1, -1), new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        // +1  0
        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));

        assertThrows(Hive.IllegalMove.class, () -> {
            board.moveStone(from, to);
        });

        assertFalse(Move.isValidMove(board, from, to));
    }
}
