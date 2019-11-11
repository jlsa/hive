package nl.josaho;
import nl.hanze.hive.IHive;

import java.util.ArrayList;
import java.util.Map;

public class HiveGame implements IHive {
    public Board board;
    public Player currentPlayer;
    public Player opponent;

    public HiveGame(Player white, Player black, Board board)
    {
        this.board = board;
        currentPlayer = white;
        opponent = black;
    }

    public HiveGame(Player white, Player black) {
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

        if (board.hasTileBeenPlacedAlready(tile)) {
            throw new IllegalMove("Tile had already been placed. Can't be placed multiple times.");
        }
        if (playerHasToPlayQueen()) {
            throw new IllegalMove("Player has to play queen.");
        }

        if (!tileIsFromPlayer(tile)) {
            throw new IllegalMove("Tile is not from player");
        }

        if (fieldIsEmpty(new Coord(q, r))) {
            throw new IllegalMove("There is a tile placed already.");
        }

        board.placeTile(new Coord(q, r), tile);
        switchPlayer();
    }

    /**
     * Move an existing tile.
     *
     * @param fromQ Q coordinate of the tile to move
     * @param fromR R coordinate of the tile to move
     * @param toQ   Q coordinate of the hexagon to move to
     * @param toR   R coordinare of the hexagon to move to
     * @throws IllegalMove If the tile could not be moved
     */
    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
        if (!isTileConnected(new Coord(toQ, toR))) {
            throw new IllegalMove("Your tile has to be attached to another tile");
        }
        if (!playerHasPlayedQueen(currentPlayer)) {
            throw new IllegalMove("You first have to play the queen");
        }

        Coord from = new Coord(fromQ, fromR);
        Field field = board.getFields().get(from);

        if (field != null) {
            Tile tile = field.peekTile();
            if (tile != null) {
                if (tile.getColor() == opponent.getPlayerColor()) {
                    throw new IllegalMove("Not allowed to move a tile that is not your own.");
                }
            }
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
        switchPlayer();
    }

    public boolean isQueenSurrounded(Player player) {
        // fix this! It is not checking for surrounding tiles
        return playerHasPlayedQueen(player);
    }
    /**
     * Check whether the given player is the winner.
     *
     * @param player Player to check
     * @return Boolean
     */
    @Override
    public boolean isWinner(Player player) {
        if (player.getPlayerColor() == currentPlayer.getPlayerColor()) {
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
        if (isWinner(currentPlayer) && isWinner(opponent)) {
            return true;
        }
        return false;
    }

    private void switchPlayer() {
        Player tmp = currentPlayer;
        currentPlayer = opponent;
        opponent = tmp;
    }

    private boolean playerHasToPlayQueen() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for(Map.Entry<Coord, Field> entry : board.getFields().entrySet()) {
            Field field = entry.getValue();

            for (Tile tile : field.getTiles()) {
                if (tile.getColor() == currentPlayer.getPlayerColor()) {
                    if (tile.getTileType() == TileType.QUEEN_BEE) {
                        return false;
                    }
                    tiles.add((tile));
                }
            }
        }
        if (tiles.size() == 3) {
            return true;
        }

        return false;
    }

    private boolean tileIsFromPlayer(Tile tile) {
        return tile.getColor() == currentPlayer.getPlayerColor();
    }

    private boolean fieldIsEmpty(Coord coord) {
        Field field = board.getFields().get(coord);
        if (field != null) {
            if (field.getTiles().length > 0) {
                return true;
            }
        }
        return false;
    }

    private boolean playerHasPlayedQueen(Player player) {
        Tile queenTile = new Tile(player.getPlayerColor(), TileType.QUEEN_BEE);
        for (Map.Entry<Coord, Field> entry : board.getFields().entrySet()) {
            Field field = entry.getValue();

            if (field.containsTile(queenTile)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTileConnected(Coord coord) {
        for (Coord c : coord.getNeighborCoords()) {
            Field field = board.getFields().get(c);
            if (field != null) {
                if (field.getTiles().length > 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
