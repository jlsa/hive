import nl.hanze.hive.Hive;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class MoveSpec {
    @Test
    void playerCanOnlyMoveItsOwnAlreadyPlacedTiles() throws Hive.IllegalMove {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);

        Board board = new Board();
        Coord from = new Coord(0, 0);
        board.placeStone(from, new Stone(whitePlayer.getPlayerColor(), Hive.Tile.BEETLE));
        board.placeStone(new Coord(0, 1), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.SOLDIER_ANT));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);
        assertThrows(Hive.IllegalMove.class, () -> {
            game.move(0, 1, 1, 0);
        });
    }

    // 5b
    @Test
    void playerCanOnlyMoveWhenQueenIsOnBoard() throws Hive.IllegalMove {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);

        Board board = new Board();
        Coord from = new Coord(0, 0);
        board.placeStone(from, new Stone(whitePlayer.getPlayerColor(), Hive.Tile.BEETLE));
        board.placeStone(new Coord(0, 1), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.SOLDIER_ANT));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);
        assertThrows(Hive.IllegalMove.class, () -> {
            game.move(0, 0, 1, 0);
        });
    }

    // 5c
    @Test
    void tileHasToBeConnectedToAtleastOneOtherTile() throws Hive.IllegalMove {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);

        Board board = new Board();
        Coord from = new Coord(0, 0);
        Coord to = new Coord(0, 3);
        board.placeStone(from, new Stone(whitePlayer.getPlayerColor(), Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(0, 1), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.QUEEN_BEE));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        assertThrows(Hive.IllegalMove.class, () -> {
            game.move(from.q, from.r, to.q, to.r);
        });
    }

    // 5d-1
    @Test
    void allTilesHaveToBeConnected() throws Hive.IllegalMove {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);

        Board board = new Board();

        Coord from = new Coord(-2, 2);
        Coord to = new Coord(-1, 1);

        board.placeStone(new Coord(-1, 0), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 1), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.QUEEN_BEE));
        board.placeStone(new Coord(-1, 2), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.SOLDIER_ANT));
        board.placeStone(new Coord(-4, 3), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.BEETLE));
        board.placeStone(new Coord(-1, 4), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.GRASSHOPPER));
        board.placeStone(new Coord(-1, 5), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.SPIDER));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        assertThrows(Hive.IllegalMove.class, () -> {
            game.move(from.q, from.r, to.q, to.r);
        });
    }
}
