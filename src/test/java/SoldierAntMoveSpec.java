import nl.josaho.stones.*;
import nl.hanze.hive.*;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SoldierAntMoveSpec {
    @Test
    void thisShouldBeSomeWhereElse() {
        // I want to know if a stone that has a stone on top of it can move.
    }

    @Test
    void soldierAntCanOnlyMoveOverEmptyFields() {
        Board board = new Board();
        Stone soldierAnt = new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT);
        Coord from = new Coord(-1, 2);
        Coord to = new Coord(1, -2);

        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(-1, 1), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));

        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(0, 1), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        board.placeStone(from, soldierAnt);

        board.moveStone(from, to);
        System.out.println(board.get(to).peekStone().toString());
        assertEquals(soldierAnt, board.get(to).peekStone());
    }

    @Test
    void soldierAntCantMoveToSpotWhereHeMovesFrom() {
        Board board = new Board();
        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(-1, 1), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));

        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(0, 1), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        board.placeStone(new Coord(-1, 2), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));

        assertFalse(Move.isValidMove(board, new Coord(-1, 2), new Coord(-1, 2)));
    }

    @Test
    void soldierAntCantMoveIntoRestrictedMoveSpace() {
        Board board = new Board();
        Stone soldierAnt = new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT);
        Coord from = new Coord(-1, 2);
        Coord to = new Coord(0, 0);

        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(-1, 1), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));

        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(0, 1), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        board.placeStone(from, soldierAnt);

        board.moveStone(from, to);
        System.out.println(board.get(to).hasStones());
        assertFalse(board.get(to).hasStones());
    }
}
