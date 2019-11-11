import nl.hanze.hive.IHive;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class MoveSpec {
    @Test
    void playerCanOnlyMoveItsOwnAlreadyPlacedTiles() throws IHive.IllegalMove {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        Player blackPlayer = new Player(IHive.PlayerColor.BLACK);

        Board board = new Board();
        Coord from = new Coord(0, 0);
        board.placeTile(from, new Tile(whitePlayer.getPlayerColor(), IHive.TileType.BEETLE));
        board.placeTile(new Coord(0, 1), new Tile(blackPlayer.getPlayerColor(), IHive.TileType.SOLDIER_ANT));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);
        assertThrows(IHive.IllegalMove.class, () -> {
            game.move(0, 1, 1, 0);
        });
    }

    @Test
    void playerCanOnlyMoveWhenQueenIsOnBoard() throws IHive.IllegalMove {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        Player blackPlayer = new Player(IHive.PlayerColor.BLACK);

        Board board = new Board();
        Coord from = new Coord(0, 0);
        board.placeTile(from, new Tile(whitePlayer.getPlayerColor(), IHive.TileType.BEETLE));
        board.placeTile(new Coord(0, 1), new Tile(blackPlayer.getPlayerColor(), IHive.TileType.SOLDIER_ANT));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);
        assertThrows(IHive.IllegalMove.class, () -> {
            game.move(0, 0, 1, 0);
        });
    }

    @Test
    void tileHasToBeConnectedToAtleastOneOtherTile() throws IHive.IllegalMove {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        Player blackPlayer = new Player(IHive.PlayerColor.BLACK);

        Board board = new Board();
        Coord from = new Coord(0, 0);
        Coord to = new Coord(0, 3);
        board.placeTile(from, new Tile(whitePlayer.getPlayerColor(), IHive.TileType.QUEEN_BEE));
        board.placeTile(new Coord(0, 1), new Tile(blackPlayer.getPlayerColor(), IHive.TileType.QUEEN_BEE));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        assertThrows(IHive.IllegalMove.class, () -> {
            game.move(from.q, from.r, to.q, to.r);
        });
    }
}
