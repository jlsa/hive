package nl.josaho.movements;

import nl.josaho.Board;
import nl.josaho.Coord;
import nl.josaho.Field;

import java.util.*;

public class SoldierAntMove {

    public static boolean isMoveAllowed(Board board, Coord from, Coord to) {
        // from the get-go check if destination is starting position
        if (from.equals(to)) {
            return false;
        }

        ArrayList<Coord> visited = new ArrayList<>();
        recursiveFindPathToDestination(board, visited, from, to);

        if (visited.contains(to)) {
            return true;
        }

        return false;
    }

    public static void recursiveFindPathToDestination(Board board, ArrayList<Coord> visited, Coord from, Coord to) {
        for (Coord next : from.getNeighborCoords()) {
            Field field = board.get(next);
            if (field.hasStones()) {
                continue;
            }
            if (!visited.contains(next)) {
                boolean shiftConnected = Move.isShiftConnected(board, from, next);
                boolean validShift = Move.isValidShift(board, from, next);
                boolean notSurrounded = !Move.isSurrounded(board, next);
                if (shiftConnected && validShift && notSurrounded) {
                    visited.add(next);
                    if (next.equals(to)) {
                        return;
                    }
                    recursiveFindPathToDestination(board, visited, next, to);
                }
            }
        }
    }
}
