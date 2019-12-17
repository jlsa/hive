package nl.josaho.stones;

import nl.josaho.Board;
import nl.josaho.Coord;

public class BeetleMove  {
    public static boolean isMoveAllowed(Board board, Coord from, Coord to) {
        // beetle is allowed to move everywhere but only 1 field away
        boolean inShiftRange = Move.inShiftRange(board, from, to, 1);
        boolean shiftIsConnected = Move.isShiftConnected(board, from, to);
        boolean isValidShift = Move.isValidShift(board, from, to);

        return shiftIsConnected && inShiftRange && isValidShift;
    }
}
