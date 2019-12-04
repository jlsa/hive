import nl.hanze.hive.*;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameSpec {
    @Test
    void whenGameIsStartedWhiteHasFirstTurn() {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        HiveGame game = new HiveGame(whitePlayer, null);
        assertEquals(whitePlayer, game.currentPlayer);
    }

    @Test
    void whenPlayerHasTurnItCanPlaceATile() throws Hive.IllegalMove {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);
        Board board = new Board();
        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        Coord coord = new Coord(0, 0);
        game.play(Hive.Tile.BEETLE, coord.q, coord.r);

        assertEquals(1, game.board.getFields().size()); // I feel this could lead to some errors..
    }

    @Test
    void whenPlayerTriesToPlaceTheSameTileAgainItThrowsAnIllegalMove() throws Hive.IllegalMove {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);
        Board board = new Board();
        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        Coord coord = new Coord(0, 0);
        game.play(Hive.Tile.BEETLE, coord.q, coord.r);

        assertThrows(Hive.IllegalMove.class, () -> {
            game.play(Hive.Tile.BEETLE, coord.q, coord.r);
        });
    }

    // 3b.1
    @Test
    void whenPlayerHasMadeAMadeAMoveSwitchPlayer() throws Hive.IllegalMove {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);
        Board board = new Board();
        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        board.placeTile(new Coord(0, 0), new Stone(whitePlayer, Hive.Tile.QUEEN_BEE));
        board.placeTile(new Coord(0, 1), new Stone(blackPlayer, Hive.Tile.QUEEN_BEE));
        board.placeTile(new Coord(0, 2), new Stone(whitePlayer, Hive.Tile.BEETLE));
        board.placeTile(new Coord(0, 3), new Stone(blackPlayer, Hive.Tile.BEETLE));

        game.move(0, 0, 0, 2);

        assertEquals(game.currentPlayer, blackPlayer);
    }

    // 3b.2
    @Test
    void whenPlayerHasPlacedANewTileSwitchPlayer() throws Hive.IllegalMove {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);
        Board board = new Board();
        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        Coord coord = new Coord(0, 0);
        game.play(Hive.Tile.BEETLE, coord.q, coord.r);

        assertEquals(game.currentPlayer, blackPlayer);
    }

    // 3b.3
    @Test
    void whenPlayerPassedTurnSwitchPlayer() throws Hive.IllegalMove {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);
        HiveGame game = new HiveGame(whitePlayer, blackPlayer);

        whitePlayer.getStones().clear();

        game.pass();

        assertEquals(game.currentPlayer, blackPlayer);
    }

    @Test
    void whenPlayerSurroundedOpponentsQueenItHasWon() {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Stone queenStone = new Stone(blackPlayer.getPlayerColor(), Hive.Tile.QUEEN_BEE);
        board.placeTile(new Coord(0, 0), queenStone);

        board.placeTile(new Coord(0, -1), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.BEETLE));
        board.placeTile(new Coord(0, 1), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.SOLDIER_ANT));
        board.placeTile(new Coord(1, -1), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.GRASSHOPPER));
        board.placeTile(new Coord(1, 0), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.SPIDER));
        board.placeTile(new Coord(-1, 0), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.BEETLE));
        board.placeTile(new Coord(-1, 1), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.GRASSHOPPER));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);
        assertTrue(game.isWinner(Hive.Player.WHITE));
    }

    @Test
    void whenBothPlayersWinItIsADraw() {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Stone queenStone = new Stone(blackPlayer.getPlayerColor(), Hive.Tile.QUEEN_BEE);
        board.placeTile(new Coord(0, 0), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.QUEEN_BEE));
        board.placeTile(new Coord(0, 0), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.QUEEN_BEE));

        board.placeTile(new Coord(0, -1), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.BEETLE));
        board.placeTile(new Coord(0, 1), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.SOLDIER_ANT));
        board.placeTile(new Coord(1, -1), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.GRASSHOPPER));
        board.placeTile(new Coord(1, 0), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.SPIDER));
        board.placeTile(new Coord(-1, 0), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.BEETLE));
        board.placeTile(new Coord(-1, 1), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.GRASSHOPPER));

        board.placeTile(new Coord(-1, -1), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.SOLDIER_ANT));
        board.placeTile(new Coord(0, -2), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.SPIDER));
        board.placeTile(new Coord(1, -2), new Stone(blackPlayer.getPlayerColor(), Hive.Tile.SOLDIER_ANT));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);
        assertTrue(game.isDraw());
    }

    // 4a
    @Test
    void playerCanOnlyPlaceItsOwnsNotPlacedTiles() throws Hive.IllegalMove {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);

        HiveGame game = new HiveGame(whitePlayer, blackPlayer);

        assertThrows(Hive.IllegalMove.class, () -> {
            game.play(Hive.Tile.BEETLE, 0, 0);
            game.play(Hive.Tile.BEETLE, 0, 0);
            game.play(Hive.Tile.BEETLE, 0, 0);
        });
    }

    // 4b
    @Test
    void firstTileHasToBePlacedOnAnEmptyField() {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);
        Board board = new Board();
        board.placeTile(new Coord(0, 0), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.BEETLE));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        assertThrows(Hive.IllegalMove.class, () -> {
            game.play(Hive.Tile.SPIDER, 0, 0);
        });
    }

    // 4e.1
    @Test
    void whenPlayerHasNotPlacedQueenAfterThreeTilePlacementsBlockMoves() throws Hive.IllegalMove {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);

        Board board = new Board();

        board.placeTile(new Coord(0, 0), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.BEETLE));
        board.placeTile(new Coord(0, 1), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.SOLDIER_ANT));
        board.placeTile(new Coord(0, 2), new Stone(whitePlayer.getPlayerColor(), Hive.Tile.GRASSHOPPER));

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        assertThrows(Hive.IllegalMove.class, () -> {
            game.play(Hive.Tile.BEETLE, -1, 0);
        });
    }

    @Test
    void playerWantsToPassButNotAllowed() throws Hive.IllegalMove {
        Player whitePlayer = new Player(Hive.Player.WHITE);
        Player blackPlayer = new Player(Hive.Player.BLACK);

        Board board = new Board();

        HiveGame game = new HiveGame(whitePlayer, blackPlayer, board);

        assertThrows(Hive.IllegalMove.class, () -> {
            game.pass();
        });
    }
}
