package nl.josaho.stones;

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
        recursiveFindPathToDestination(board, visited, from, to, 0);

        if (visited.contains(to)) {
            return true;
        }

        return false;
    }

    public static void recursiveFindPathToDestination(Board board, ArrayList<Coord> visited, Coord from, Coord to, int depth) {
        for (Coord next : from.getNeighborCoords()) {
            Field f = board.get(next);
                if (f.hasStones()) {
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
                    recursiveFindPathToDestination(board, visited, next, to, depth + 1);
                }
            }
        }
    }


    public static ArrayList<ArrayList<Coord>> findPathsToDestination(Board board, Coord from, Coord to, int depth) {
        ArrayList<ArrayList<Coord>> paths = new ArrayList<>();

        Stack<Coord> frontier = new Stack<>();
        frontier.add(from);

        ArrayList<Coord> visited = new ArrayList<>();
        visited.add(from);

        while (!frontier.empty()) {
            Coord current = frontier.pop();
            System.out.println("visiting " + current.toString());
            for (Coord next : Board.getNeighborWithStones(board, current)) {
                if (next.equals(to)) {
                    break;
                }
                if (Move.isShiftConnected(board, current, next) && Move.isValidShift(board, current, next) && !Move.isSurrounded(board, next)) {
                    System.out.println("valid " + next.toString());
                }
                Field f = board.get(next);
                if (f.hasStones()) {
                    if (!visited.contains(next)) {
                        frontier.add(next);
                        visited.add(next);
                    }
                }
            }
        }

        return paths;
    }



    public static ArrayList<Coord> findAllowedBoardCoords(Board board, Coord from, ArrayList<Coord> coords, int depth) {
        for (Coord to : from.getNeighborCoords()) {
            if (from.equals(to)) {
                continue;
            }
            Field f = board.get(to);
            if (f.hasStones()) {
                continue;
            }
            if (!coords.contains(to)) {
                if (Move.isShiftConnected(board, from, to) && Move.isValidShift(board, from, to) && !Move.isSurrounded(board, to)) {
                    for (int i = 0; i < depth; i++) {
                        System.out.print(" ");
                    }
                    System.out.print("depth: " + depth + " from: " + from.toString() + " neighbor: " + to.toString());
                    System.out.println();
                    coords.add(to);
                    findAllowedBoardCoords(board, to, coords, depth + 1);
                }
            }

        }

        return coords;
    }
}
