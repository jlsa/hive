import nl.hanze.hive.IHive;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameSpec {
    @Test
    void whenGameIsStartedWhiteHasFirstTurn() {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        HiveGame game = new HiveGame(whitePlayer, null);
        assertEquals(whitePlayer, game.currentPlayer);
    }

    @Test
    void whenPlayerHasTurnItCanPlaceATile() throws IHive.IllegalMove {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        Player blackPlayer = new Player(IHive.PlayerColor.BLACK);
        Board board = new Board();
        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        Coord coord = new Coord(0, 0);
        Tile tile = new Tile(whitePlayer.getPlayerColor(), IHive.TileType.BEETLE);
        game.play(tile, coord.q, coord.r);

        assertEquals(1, game.board.getFields().size()); // I feel this could lead to some errors..
    }

    @Test
    void whenPlayerTriesToPlaceTheSameTileAgainItThrowsAnIllegalMove() throws IHive.IllegalMove {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        Player blackPlayer = new Player(IHive.PlayerColor.BLACK);
        Board board = new Board();
        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        Coord coord = new Coord(0, 0);
        Tile tile = new Tile(whitePlayer.getPlayerColor(), IHive.TileType.BEETLE);
        game.play(tile, coord.q, coord.r);

        assertThrows(IHive.IllegalMove.class, () -> {
            game.play(tile, coord.q, coord.r);
        });
    }

    // 3b.1
    @Test
    void whenPlayerHasMadeAMadeAMoveSwitchPlayer() throws IHive.IllegalMove {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        Player blackPlayer = new Player(IHive.PlayerColor.BLACK);
        Board board = new Board();
        Tile tile = new Tile(whitePlayer.getPlayerColor(), IHive.TileType.BEETLE);
        Coord from = new Coord(0, 0);
        board.placeTile(from, tile);

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        Coord to = new Coord(1, 0);

        game.move(from.q, from.r, to.q, to.r);

        assertEquals(game.currentPlayer, blackPlayer);
    }

    // 3b.2
    @Test
    void whenPlayerHasPlacedANewTileSwitchPlayer() throws IHive.IllegalMove {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        Player blackPlayer = new Player(IHive.PlayerColor.BLACK);
        Board board = new Board();
        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        Coord coord = new Coord(0, 0);
        Tile tile = new Tile(whitePlayer.getPlayerColor(), IHive.TileType.BEETLE);
        game.play(tile, coord.q, coord.r);

        assertEquals(game.currentPlayer, blackPlayer);
    }

    // 3b.3
    @Test
    void whenPlayerPassedTurnSwitchPlayer() throws IHive.IllegalMove {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        Player blackPlayer = new Player(IHive.PlayerColor.BLACK);
        HiveGame game = new HiveGame(whitePlayer, blackPlayer);
        game.pass();

        assertEquals(game.currentPlayer, blackPlayer);
    }

    @Test
    void whenPlayerSurroundedOpponentsQueenItHasWon() {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        Player blackPlayer = new Player(IHive.PlayerColor.BLACK);
        Board board = new Board();
        Tile queenTile = new Tile(blackPlayer.getPlayerColor(), IHive.TileType.QUEEN_BEE);
        board.placeTile(new Coord(0, 0), queenTile);

        board.placeTile(new Coord(0, -1), new Tile(whitePlayer.getPlayerColor(), IHive.TileType.BEETLE));
        board.placeTile(new Coord(0, 1), new Tile(whitePlayer.getPlayerColor(), IHive.TileType.SOLDIER_ANT));
        board.placeTile(new Coord(1, -1), new Tile(whitePlayer.getPlayerColor(), IHive.TileType.GRASSHOPPER));
        board.placeTile(new Coord(1, 0), new Tile(whitePlayer.getPlayerColor(), IHive.TileType.SPIDER));
        board.placeTile(new Coord(-1, 0), new Tile(blackPlayer.getPlayerColor(), IHive.TileType.BEETLE));
        board.placeTile(new Coord(-1, 1), new Tile(blackPlayer.getPlayerColor(), IHive.TileType.GRASSHOPPER));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);
        assertTrue(game.isWinner(whitePlayer));
    }

    @Test
    void whenBothPlayersWinItIsADraw() {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        Player blackPlayer = new Player(IHive.PlayerColor.BLACK);
        Board board = new Board();
        Tile queenTile = new Tile(blackPlayer.getPlayerColor(), IHive.TileType.QUEEN_BEE);
        board.placeTile(new Coord(0, 0), new Tile(blackPlayer.getPlayerColor(), IHive.TileType.QUEEN_BEE));
        board.placeTile(new Coord(0, 0), new Tile(whitePlayer.getPlayerColor(), IHive.TileType.QUEEN_BEE));

        board.placeTile(new Coord(0, -1), new Tile(whitePlayer.getPlayerColor(), IHive.TileType.BEETLE));
        board.placeTile(new Coord(0, 1), new Tile(whitePlayer.getPlayerColor(), IHive.TileType.SOLDIER_ANT));
        board.placeTile(new Coord(1, -1), new Tile(whitePlayer.getPlayerColor(), IHive.TileType.GRASSHOPPER));
        board.placeTile(new Coord(1, 0), new Tile(whitePlayer.getPlayerColor(), IHive.TileType.SPIDER));
        board.placeTile(new Coord(-1, 0), new Tile(blackPlayer.getPlayerColor(), IHive.TileType.BEETLE));
        board.placeTile(new Coord(-1, 1), new Tile(blackPlayer.getPlayerColor(), IHive.TileType.GRASSHOPPER));

        board.placeTile(new Coord(-1, -1), new Tile(blackPlayer.getPlayerColor(), IHive.TileType.SOLDIER_ANT));
        board.placeTile(new Coord(0, -2), new Tile(blackPlayer.getPlayerColor(), IHive.TileType.SPIDER));
        board.placeTile(new Coord(1, -2), new Tile(blackPlayer.getPlayerColor(), IHive.TileType.SOLDIER_ANT));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);
        assertTrue(game.isDraw());
    }

    // 4a
    @Test
    void playerCanOnlyPlaceItsOwnsNotPlacedTiles() throws IHive.IllegalMove {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        Player blackPlayer = new Player(IHive.PlayerColor.BLACK);

        HiveGame game = new HiveGame(whitePlayer, blackPlayer);
        Tile tile = new Tile(blackPlayer.getPlayerColor(), IHive.TileType.BEETLE);

        assertThrows(IHive.IllegalMove.class, () -> {
            game.play(tile, 0, 0);
        });
    }

    // 4b
    @Test
    void firstTileHasToBePlacedOnAnEmptyField() {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        Player blackPlayer = new Player(IHive.PlayerColor.BLACK);
        Board board = new Board();
        board.placeTile(new Coord(0, 0), new Tile(whitePlayer.getPlayerColor(), IHive.TileType.BEETLE));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);
        Tile tile = new Tile(whitePlayer.getPlayerColor(), IHive.TileType.SPIDER);

        assertThrows(IHive.IllegalMove.class, () -> {
            game.play(tile, 0, 0);
        });
    }

    // 4c
    @Test
    void whenThereAreTilesOnBoardNewTileHasToBeConnected() {

    }

    // 4d
    @Test
    void whenThereAreTilesFromBothPlayerOnTheBoardANewTileCannotBePlacedNextToAnOpponentsTile() {

    }

    // 4e.1
    @Test
    void whenPlayerHasNotPlacedQueenAfterThreeTilePlacementsBlockMoves() throws IHive.IllegalMove {
        Player whitePlayer = new Player(IHive.PlayerColor.WHITE);
        Player blackPlayer = new Player(IHive.PlayerColor.BLACK);

        Board board = new Board();

        board.placeTile(new Coord(0, 0), new Tile(whitePlayer.getPlayerColor(), IHive.TileType.BEETLE));
        board.placeTile(new Coord(0, 1), new Tile(whitePlayer.getPlayerColor(), IHive.TileType.SOLDIER_ANT));
        board.placeTile(new Coord(0, 2), new Tile(whitePlayer.getPlayerColor(), IHive.TileType.GRASSHOPPER));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        assertThrows(IHive.IllegalMove.class, () -> {
            game.play(new Tile(whitePlayer.getPlayerColor(), IHive.TileType.BEETLE), -1, 0);
        });
    }
}
