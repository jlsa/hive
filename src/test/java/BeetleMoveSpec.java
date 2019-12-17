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

    @Test
    void beetleGateFails() throws Hive.IllegalMove {
        Board board = new Board();
        Player white = new Player(Hive.Player.WHITE);
        Player black = new Player(Hive.Player.BLACK);
        HiveGame game = new HiveGame(white, black, board);

        board.placeStone(new Coord(0, 0), new Stone(white, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 0), new Stone(white, Hive.Tile.SPIDER));
        board.placeStone(new Coord(-1, -1), new Stone(white, Hive.Tile.BEETLE));
        board.placeStone(new Coord(0, -2), new Stone(black, Hive.Tile.SPIDER));
        board.placeStone(new Coord(1, -2), new Stone(black, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, -1), new Stone(black, Hive.Tile.SOLDIER_ANT));

        assertThrows(Hive.IllegalMove.class, () -> {
           game.move(-1, -1, 0, -1);
        });
    }

    @Test
    void beetleGateSucceeds() throws Hive.IllegalMove {
        Board board = new Board();
        Player white = new Player(Hive.Player.WHITE);
        Player black = new Player(Hive.Player.BLACK);
        HiveGame game = new HiveGame(white, black, board);
        Coord to = new Coord(0, -1);

        board.placeStone(new Coord(0, 0), new Stone(white, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 0), new Stone(white, Hive.Tile.SPIDER));
        board.placeStone(new Coord(-1, -1), new Stone(white, Hive.Tile.BEETLE));
        board.placeStone(new Coord(-1, -1), new Stone(white, Hive.Tile.BEETLE));

        board.placeStone(new Coord(0, -2), new Stone(black, Hive.Tile.SPIDER));
        board.placeStone(new Coord(1, -2), new Stone(black, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, -1), new Stone(black, Hive.Tile.SOLDIER_ANT));

        game.move(-1, -1, to.q, to.r);

        assertEquals(new Stone(white, Hive.Tile.BEETLE), board.get(to).peekStone());
    }

    @Test
    void beetleGateWhereThereIsAStonePlacedAtDestinationAndItSucceeds() throws Hive.IllegalMove {
        Board board = new Board();
        Player white = new Player(Hive.Player.WHITE);
        Player black = new Player(Hive.Player.BLACK);
        HiveGame game = new HiveGame(white, black, board);
        Coord to = new Coord(0, -1);

        board.placeStone(new Coord(0, 0), new Stone(white, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 0), new Stone(white, Hive.Tile.SPIDER));
        board.placeStone(new Coord(-1, -1), new Stone(white, Hive.Tile.BEETLE));

        board.placeStone(new Coord(0, -1), new Stone(black, Hive.Tile.BEETLE));
        board.placeStone(new Coord(0, -2), new Stone(black, Hive.Tile.SPIDER));
        board.placeStone(new Coord(1, -2), new Stone(black, Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(1, -1), new Stone(black, Hive.Tile.SOLDIER_ANT));

        game.move(-1, -1, to.q, to.r);

        assertEquals(new Stone(white, Hive.Tile.BEETLE), board.get(to).peekStone());
    }

}
