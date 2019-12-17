package nl.josaho;
import nl.hanze.hive.Hive;
import nl.josaho.stones.Move;

import java.util.*;

public class HiveGame implements Hive {
    public Board board;
    public nl.josaho.Player currentPlayer;
    public nl.josaho.Player opponent;

    public HiveGame(nl.josaho.Player white, nl.josaho.Player black, Board board) {
        this.board = board;
        currentPlayer = white;
        opponent = black;
    }

    public HiveGame(nl.josaho.Player white, nl.josaho.Player black) {
        this(white, black, new Board());
    }

    /**
     * Play a new tile.
     *
     * @param tile Tile to play
     * @param q    Q coordinate of hexagon to play to
     * @param r    R coordinate of hexagon to play to
     * @throws IllegalMove If the tile could not be played
     */
    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {
        if (currentPlayer.amountOfStonesOfTile(tile) == 0) {
            throw new IllegalMove("You do not own any of these tiles anymore");
        }

        if (playerHasToPlayQueen()) {
            throw new IllegalMove("Player has to play queen.");
        }

        if (!stoneIsFromPlayer(tile, currentPlayer)) {
            throw new IllegalMove("Tile is not from player");
        }

        if (fieldIsEmpty(new Coord(q, r))) {
            throw new IllegalMove("There is a tile placed already.");
        }

        Stone stone = currentPlayer.playStone(tile);
        if (stone == null) {
            throw new IllegalMove("Cannot play stone from hand of the player");
        }

        if (!board.placeStone(new Coord(q, r), stone)) {
            throw new IllegalMove("Place on board failed");
        }
        switchPlayer();
    }

    /**
     * Move an existing tile.
     *
     * @param fromQ Q coordinate of the tile to move
     * @param fromR R coordinate of the tile to move
     * @param toQ   Q coordinate of the hexagon to move to
     * @param toR   R coordinate of the hexagon to move to
     * @throws IllegalMove If the tile could not be moved
     */
    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
        Coord from = new Coord(fromQ, fromR);
        Coord to = new Coord(toQ, toR);

        if (!isStoneConnected(to)) {
            throw new IllegalMove("Your tile has to be attached to another tile");
        }
        if (!playerHasPlayedQueen(currentPlayer)) {
            throw new IllegalMove("You first have to play the queen");
        }

        Field field = board.get(from);

        if (field != null) {
            if (field.hasStones()) {
                Stone stone = field.peekStone();
                if (stone != null) {
                    if (stone.getColor() == opponent.getPlayerColor()) {
                        throw new IllegalMove("Not allowed to move a tile that is not your own.");
                    }
                }
            }
        }

        try {
            board.moveStone(from, to);
        } catch (IllegalMove e) {
            throw e;
        }

        if (!board.boardIsOneSwarm()) {
            board.moveStone(to, from);
            throw new IllegalMove("Not all stones are connected.");
        }

        switchPlayer();
    }


    /**
     * Pass the turn.
     *
     * @throws IllegalMove If the turn could not be passed
     */
    @Override
    public void pass() throws IllegalMove {
        if (!this.currentPlayer.canPass()) {
            throw new IllegalMove();
        }

        switchPlayer();
    }

    public boolean isQueenSurrounded(nl.josaho.Player player) {
        Stone queenStone = new Stone(player.getPlayerColor(), Hive.Tile.QUEEN_BEE);
        for (Map.Entry<Coord, Field> entry : board.getFields().entrySet()) {
            Field field = entry.getValue();
            Coord coord = entry.getKey();

            if (field.containsStone(queenStone)) {
                if (Move.isSurrounded(board, coord, 6)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Check whether the given player is the winner.
     *
     * @param player Player to check
     * @return Boolean
     */
    @Override
    public boolean isWinner(Player player) {
        if (player == currentPlayer.getPlayerColor()) {
            return isQueenSurrounded(opponent);
        } else {
            return isQueenSurrounded(currentPlayer);
        }
    }

    /**
     * Check whether the game is a draw.
     *
     * @return Boolean
     */
    @Override
    public boolean isDraw() {
        if (isWinner(Player.BLACK) && isWinner(Player.WHITE)) {
            System.out.println("draw");
            return true;
        }
        return false;
    }

    private void switchPlayer() {
        nl.josaho.Player tmp = currentPlayer;
        currentPlayer = opponent;
        opponent = tmp;
    }

    private boolean playerHasToPlayQueen() {
        ArrayList<Stone> stones = new ArrayList<>();
        for(Map.Entry<Coord, Field> entry : board.getFields().entrySet()) {
            Field field = entry.getValue();

            for (Stone stone : field.getStones()) {
                if (stone.getColor() == currentPlayer.getPlayerColor()) {
                    if (stone.getTile() == Hive.Tile.QUEEN_BEE) {
                        return false;
                    }
                    stones.add((stone));
                }
            }
        }
        if (stones.size() == 3) {
            return true;
        }

        return false;
    }

    private boolean stoneIsFromPlayer(Tile tile, nl.josaho.Player player) {
        for (Stone stone : player.getStones()) {
            if (stone.getTile() == tile) {
                return true;
            }
        }
        return false;
    }

    private boolean fieldIsEmpty(Coord coord) {
        Field field = board.getFields().get(coord);
        if (field != null) {
            if (field.getStones().length > 0) {
                return true;
            }
        }
        return false;
    }

    private boolean playerHasPlayedQueen(nl.josaho.Player player) {
        Stone queenStone = new Stone(player.getPlayerColor(), Hive.Tile.QUEEN_BEE);
        for (Map.Entry<Coord, Field> entry : board.getFields().entrySet()) {
            Field field = entry.getValue();

            if (field.containsStone(queenStone)) {
                return true;
            }
        }
        return false;
    }

    private boolean isStoneConnected(Coord coord) {
        for (Coord c : coord.getNeighborCoords()) {
            Field field = board.getFields().get(c);
            if (field != null) {
                if (field.hasStones()) {
                    return true;
                }
            }
        }

        return false;
    }
}
