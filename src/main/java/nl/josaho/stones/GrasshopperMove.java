package nl.josaho.stones;

import nl.josaho.Board;
import nl.josaho.Coord;

public class GrasshopperMove {
    public static boolean isMoveAllowed(Board board, Coord from, Coord to) {
        boolean inStraightLine = isStraightLine(from, to);
        return inStraightLine;
//        return Move.isValidShift(board, from, to);
    }

    public static boolean isStraightLine(Coord from, Coord to) {
        Coord diff = new Coord(Math.abs(from.q - to.q), Math.abs(from.r - to.r));
        return (diff.r == 0) || (diff.q == diff.r);
    }
}
