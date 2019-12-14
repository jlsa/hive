package nl.josaho.stones;

import nl.josaho.*;

public class QueenBeeMove {

    public static boolean isMoveAllowed(Board board, Coord from, Coord to) {
        // check if to field has stones
        if (board.get(to).hasStones() || !Move.inShiftRange(board, from, to, 1)) {
            return false;
        }

        return Move.isValidShift(board, from, to) && Move.isShiftConnected(board, from, to);
    }
}
