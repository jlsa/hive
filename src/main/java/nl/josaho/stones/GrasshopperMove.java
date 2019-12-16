package nl.josaho.stones;

import nl.josaho.Board;
import nl.josaho.Coord;

public class GrasshopperMove {
    public static boolean isMoveAllowed(Board board, Coord from, Coord to) {

        // check jump to a field that is not allowed based on jump rules
        // like distance should be atleast higher than one

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
        Coord diff = new Coord(Math.abs(from.q - to.q), Math.abs(from.r - to.r));
        return (diff.r == 0 && (diff.q != diff.r)) || (diff.q == diff.r);
    }

    public static boolean lineHasEmptyField(Board board, Coord from, Coord to) {
        if (isStraightLine(from, to)) {
            Coord diff = new Coord(Math.abs(from.q - to.q), Math.abs(from.r - to.r));

            if (diff.r == 0) {
                for (int i = 0; i < diff.q; i++) {
                    Coord current = new Coord(from.q + i, from.r);
                    if (!board.get(current).hasStones()) {
                        System.out.println("empty -> " + current);
                        return true;
                    }
                }
            } else if (diff.q == diff.r) {
                for (int i = 0; i < diff.q; i++) {
                    Coord current = new Coord(from.q + i, from.r + i);
                    if (!board.get(current).hasStones()) {
                        System.out.println("empty -> " + current);
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
