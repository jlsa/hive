import nl.hanze.hive.*;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SoldierAntMoveSpec {
    @Test
    void soldierAntCanOnlyMoveOverEmptyFields() throws Hive.IllegalMove {
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
        assertEquals(soldierAnt, board.get(to).peekStone());
    }

    @Test
    void soldierAntCantMoveToSpotWhereHeMovesFrom() throws Hive.IllegalMove {
        Board board = new Board();
        Coord from = new Coord(-1, 2);
        Coord to = new Coord(-1, 2);

        board.placeStone(new Coord(1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(-1, 1), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));

        board.placeStone(new Coord(0, -1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(0, 1), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        board.placeStone(new Coord(-1, 2), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));

        assertThrows(Hive.IllegalMove.class, () -> {
            board.moveStone(from, to);
        });
    }

    @Test
    void soldierAntCantMoveIntoRestrictedMoveSpace() throws Hive.IllegalMove {
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

        assertThrows(Hive.IllegalMove.class, () -> {
            board.moveStone(from, to);
        });
    }
}
