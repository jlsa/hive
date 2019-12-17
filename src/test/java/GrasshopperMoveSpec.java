import nl.josaho.movements.*;
import nl.hanze.hive.*;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GrasshopperMoveSpec {
    @Test
    void grasshopperMoveOverQAndRAndSLines() throws Hive.IllegalMove {
        Board board = new Board();
        Player white = new Player(Hive.Player.WHITE);
        Player black = new Player(Hive.Player.BLACK);
        Coord from = new Coord(0, 0);
        Coord r = new Coord(-2, 0);
        Coord q = new Coord(0, -2);
        Coord s = new Coord(2, -2);
        Stone grasshopper = new Stone(black, Hive.Tile.GRASSHOPPER);

        board.placeStone(from, grasshopper);
        board.placeStone(new Coord(1, 0), new Stone(black, Hive.Tile.BEETLE));
        board.placeStone(new Coord(2, -1), new Stone(black, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(3, -1), new Stone(black, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(3, -2), new Stone(black, Hive.Tile.QUEEN_BEE));

        board.placeStone(new Coord(-1, 0), new Stone(white, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(0, -1), new Stone(white, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(1, -1), new Stone(white, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(1, -2), new Stone(white, Hive.Tile.SPIDER));

        board.moveStone(from, r);
        board.moveStone(r, from);

        board.moveStone(from, q);
        board.moveStone(q, from);

        board.moveStone(from, s);
        board.moveStone(s, from);

        assertEquals(grasshopper, board.get(from).peekStone());
    }

    @Test
    void grasshopperMovesInStraightLine() throws Hive.IllegalMove {
        Board board = new Board();
        Coord from = new Coord(-1, 0);
        Coord to = new Coord(-3, 2);

        Stone grasshopper = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);

        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, -1), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(-1, 1), new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(0, 2), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        board.placeStone(new Coord(0, 3), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));
        board.placeStone(new Coord(-2, 4), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));

        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(0, 1), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));
        board.placeStone(new Coord(-2, 1), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        board.placeStone(new Coord(-1, 2), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 4), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));

        board.placeStone(from, grasshopper);
        board.moveStone(from, to);

        assertEquals(grasshopper, board.get(to).peekStone());
    }

    @Test
    void grasshopperCantMoveToFieldWhereItCameFrom() {
        Board board = new Board();
        Coord from = new Coord(-1, 0);
        Coord to = new Coord(-1, 0);

        Stone grasshopper = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);

        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, -1), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(-1, 1), new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(0, 2), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        board.placeStone(new Coord(0, 3), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));
        board.placeStone(new Coord(-2, 4), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));

        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(0, 1), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));
        board.placeStone(new Coord(-2, 1), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        board.placeStone(new Coord(-1, 2), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 4), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));

        board.placeStone(from, grasshopper);

        assertThrows(Hive.IllegalMove.class, () -> {
            board.moveStone(from, to);
        });
    }

    @Test
    void grasshopperHasToJumpOverAtleastOneStone() {
        Board board = new Board();
        Coord from = new Coord(-1, 0);
        Coord to = new Coord(0, 0);

        Stone grasshopper = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);

        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, -1), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(-1, 1), new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(0, 2), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        board.placeStone(new Coord(0, 3), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));
        board.placeStone(new Coord(-2, 4), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));

        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(0, 1), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));
        board.placeStone(new Coord(-2, 1), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        board.placeStone(new Coord(-1, 2), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 4), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));

        board.placeStone(from, grasshopper);

        assertFalse(Move.isValidMove(board, from, to));
    }

    @Test
    void grasshopperCantMoveToOccupiedField() {
        Board board = new Board();
        Coord from = new Coord(-1, 0);
        Coord to = new Coord(-1, 2);

        Stone grasshopper = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);

        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, -1), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(-1, 1), new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(0, 2), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        board.placeStone(new Coord(0, 3), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));
        board.placeStone(new Coord(-2, 4), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));

        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(0, 1), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));
        board.placeStone(new Coord(-2, 1), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        board.placeStone(new Coord(-1, 2), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 4), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));

        board.placeStone(from, grasshopper);

        assertFalse(Move.isValidMove(board, from, to));
    }

    @Test
    void grasshopperCantHopOverEmptyFieldsOnRLine() {
        Board board = new Board();
        Coord from = new Coord(-1, 0);
        Coord to = new Coord(3, 0);

        Stone grasshopper = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);

        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, -1), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(2, -1), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));

        board.placeStone(new Coord(0, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(2, 0), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));

        board.placeStone(from, grasshopper);

        assertThrows(Hive.IllegalMove.class, () -> {
            board.moveStone(from, to);
        });
    }

    @Test
    void grasshopperCantHopOverEmptyFieldsOnQLine() {
        Board board = new Board();
        Coord from = new Coord(-1, 0);
        Coord to = new Coord(3, -4);

        Stone grasshopper = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);

        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, -1), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(2, -3), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));

        board.placeStone(new Coord(0, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(2, -2), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));

        board.placeStone(from, grasshopper);

        assertThrows(Hive.IllegalMove.class, () -> {
            board.moveStone(from, to);
        });
    }

    @Test
    void grasshopperCantHopOverEmptyFieldsOnSLine() {
        Board board = new Board();
        Coord from = new Coord(1, 0);
        Coord to = new Coord(1, -4);

        Stone grasshopper = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);

        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, -1), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(1, -3), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));

        board.placeStone(new Coord(0, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(0, -2), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));

        board.placeStone(from, grasshopper);

        assertThrows(Hive.IllegalMove.class, () -> {
            board.moveStone(from, to);
        });
    }
}
