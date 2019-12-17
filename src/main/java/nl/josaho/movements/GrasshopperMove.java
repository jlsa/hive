package nl.josaho.movements;

import nl.josaho.Board;
import nl.josaho.Coord;

public class GrasshopperMove {
    public static boolean isMoveAllowed(Board board, Coord from, Coord to) {
        if (from.equals(to)) {
            return false;
        }
        if (board.get(to).hasStones()) {
            return false;
        }

        if (!isStraightLine(from, to)) {
            return false;
        }

        if (lineHasEmptyField(board, from, to)) {
            return false;
        }

        return Move.isValidShift(board, from, to);
    }

    public static boolean isStraightLine(Coord from, Coord to) {
        Coord diff = new Coord(from.q - to.q, from.r - to.r);
        int s = -diff.q - diff.r;
        return diff.r == 0 || diff.q == 0 || s == 0;
    }

    public static boolean lineHasEmptyField(Board board, Coord from, Coord to) {
        Coord dirQUp = new Coord(0, -1);
        Coord dirQDown = new Coord(0, 1);
        Coord dirRUp = new Coord(1, 0);
        Coord dirRDown = new Coord(-1, 0);
        Coord dirSUp = new Coord(-1, 1);
        Coord dirSDown = new Coord(1, -1);

        int q = from.q - to.q;
        int r = from.r - to.r;
        int s = -q - r;

        int stepsToTake = 0;
        Coord direction = null;
        if (q == 0) {
            stepsToTake = Math.abs(r);
            if (r > 0) {
                direction = dirQUp;
            } else {
                direction = dirQDown;
            }
        }

        if (r == 0) {
            stepsToTake = Math.abs(q);
            if (q > 0) {
                direction = dirRDown;
            } else {
                direction = dirRUp;
            }
        }

        if (s == 0) {
            stepsToTake = Math.abs(q);
            if (r > 0) {
                direction = dirSDown;
            } else {
                direction = dirSUp;
            }
        }

        Coord current = from;
        for (int i = 0; i < stepsToTake-1; i++) {
            current = new Coord(current.q + direction.q, current.r + direction.r);
            if (!board.get(current).hasStones()) {
                return true;
            }
        }

        return false;
    }
}
