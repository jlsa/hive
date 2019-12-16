package nl.josaho.stones;

import nl.josaho.*;

import java.util.*;

// Spider Move!
// Spider Move!
// Does whatever a Spider Move does!
// Can it swing from a web?

// No it can't
// It's a Move
// Lookout!
// It's the Spider Move!!
public class SpiderMove {
    public static boolean isMoveAllowed(Board board, Coord from, Coord to) {
        // from the get-go check if destination is starting position
        if (from.equals(to)) {
            return false;
        }

        Stack<Coord> path = BFS(board, from, to);
        if (path.size() == 3) {
            return true;
        }

        return false;
    }

    public static Stack<Coord> BFS(Board board, Coord start, Coord goal) {
        Stack<Coord> frontier = new Stack<>();
        frontier.add(start);

        HashMap<Coord, Coord> cameFrom = new HashMap<>();
        cameFrom.put(start, null);

        while (!frontier.empty()) {
            Coord current = frontier.pop();

            for (Coord next: current.getNeighborCoords()) {
                Field f = board.get(next);
                if (f.hasStones()) {
                    continue;
                }
                if (!cameFrom.containsKey(next)) {
                    boolean shiftConnected = Move.isShiftConnected(board, current, next);
                    boolean validShift = Move.isValidShift(board, current, next);
                    boolean notSurrounded = !Move.isSurrounded(board, next);
                    if (shiftConnected && validShift && notSurrounded) {
                        frontier.add(next);
                        cameFrom.put(next, current);
                    }
                }
                if (current.equals(goal)) {
                    break;
                }
            }
            if (current.equals(goal)) {
                break;
            }
        }

        return reconstructPath(cameFrom, start, goal);
    }

    public static Stack<Coord> reconstructPath(HashMap<Coord, Coord> visited, Coord start, Coord goal) {
        Stack<Coord> path = new Stack<>();

        Coord current = goal;
        while (!current.equals(start)) {
            path.add(current);
            current = visited.get(current);
        }

        // should be reversed if i want to recreate the path for real.

        return path;
    }
}
