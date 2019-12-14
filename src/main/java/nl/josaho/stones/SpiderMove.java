package nl.josaho.stones;

import nl.josaho.Board;
import nl.josaho.Coord;

public class SpiderMove {
    public static boolean isMoveAllowed(Board board, Coord from, Coord to) {
        return Move.isValidShift(board, from, to);
    }
}
