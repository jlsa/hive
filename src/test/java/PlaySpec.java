import nl.hanze.hive.Hive;
import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlaySpec {
    @Test
    void playGameWhereWhiteWins() throws Hive.IllegalMove {
        Player white = new Player(Hive.Player.WHITE);
        Player black = new Player(Hive.Player.BLACK);
        Board board = new Board();
        HiveGame game = new HiveGame(white, black, board);

        game.play(Hive.Tile.BEETLE, 0, 0); // w
        game.play(Hive.Tile.BEETLE, 1, 0); // b
        game.play(Hive.Tile.SPIDER, 0, -1); // w
        game.play(Hive.Tile.QUEEN_BEE, 1, 1); // b
        game.play(Hive.Tile.QUEEN_BEE, -1, 0); // w
        assertThrows(Hive.IllegalMove.class, () -> {
            game.move(0, 0, 1, -1); // b
        });

    }

    @Test
    void playGameWhereBlackWins() {

    }

    @Test
    void playGameWhereBothWin() {

    }

    @Test
    void playGameWhereBothPlayDraw() {

    }
}
