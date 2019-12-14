package nl.josaho.stones;

import nl.josaho.*;

public class QueenBeeMove {

    public static boolean isMoveAllowed(Board board, Coord from, Coord to) {
        return Move.isValidShift(board, from, to) && Move.isShiftConnected(board, from, to);
    }
}
