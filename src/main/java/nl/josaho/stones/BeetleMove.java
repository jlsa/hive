package nl.josaho.stones;

import nl.josaho.Board;
import nl.josaho.Coord;

public class BeetleMove  {
    public static boolean isMoveAllowed(Board board, Coord from, Coord to) {
        // beetle is allowed to move everywhere but only 1 field away
        return Move.isShiftConnected(board, from, to) && Move.inShiftRange(board, from, to, 1);
    }
}
