import nl.josaho.stones.*;
import nl.hanze.hive.*;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GrasshopperMoveSpec {
    @Test
    void grasshopperMovesInStraightLine() {
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
    void grasshopperCantMoveToFieldWhereItAlreadyIs() {

    }

    @Test
    void grasshopperHasToJumpOverAtleastOneStone() {

    }

    @Test
    void grasshopperCantMoveToOccupiedField() {

    }

    @Test
    void grasshopperCantHopOverEmptyFields() {

    }
}
