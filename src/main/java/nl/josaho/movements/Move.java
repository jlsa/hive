package nl.josaho.movements;

import nl.hanze.hive.Hive;
import nl.josaho.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Move {

    public static boolean isValidMove(Board board, Coord from, Coord to) {
        Stone stone = board.get(from).peekStone();

        if (stone.getTile().equals(Hive.Tile.QUEEN_BEE)) {
            return QueenBeeMove.isMoveAllowed(board, from, to);
        }
        if (stone.getTile().equals(Hive.Tile.GRASSHOPPER)) {
            return GrasshopperMove.isMoveAllowed(board, from, to);
        }
        if (stone.getTile().equals(Hive.Tile.SOLDIER_ANT)) {
            return SoldierAntMove.isMoveAllowed(board, from, to);
        }
        if (stone.getTile().equals(Hive.Tile.BEETLE)) {
            return BeetleMove.isMoveAllowed(board, from, to);
        }
        if (stone.getTile().equals(Hive.Tile.SPIDER)) {
            return SpiderMove.isMoveAllowed(board, from, to);
        }

        return false;
    }

    public static boolean isValidShift(Board board, Coord from, Coord to) {
        // min(h(n1), h(n2)) <= max(h(a) - 1, h(b))
        int mini = -1;
        int maxi = 0;

        Field a = board.get(from);
        Field b = board.get(to);

        ArrayList<Coord> neighbors = (ArrayList<Coord>) Arrays.stream(from.getNeighborCoords())
                .filter(s -> Arrays.asList(to.getNeighborCoords()).contains(s))
                .collect(Collectors.toList());

        if (neighbors.size() >= 2) {
            mini = Math.min(board.get(neighbors.get(0)).height(), board.get(neighbors.get(1)).height());
            maxi = Math.max(a.height() - 1, b.height());
        }

        return (mini <= maxi);
    }

    public static boolean isShiftConnected(Board board, Coord from, Coord to) {
        // if it has equal neighbor let it pass
        ArrayList<Coord> neighborsFrom = new ArrayList<>();
        ArrayList<Coord> neighborsTo = new ArrayList<>();

        for (Coord c : from.getNeighborCoords()) {
            Field f = board.get(c);
            if (f.hasStones()) {
                neighborsFrom.add(c);
            }
        }

        for (Coord c : to.getNeighborCoords()) {
            Field f = board.get(c);
            if (f.hasStones()) {
                neighborsTo.add(c);
            }
        }

        if (neighborsTo.size() > 0 && neighborsFrom.size() > 0) {
            ArrayList<Coord> neighbors = (ArrayList<Coord>) neighborsFrom.stream()
                    .filter(s -> neighborsTo.contains(s))
                    .collect(Collectors.toList());
            if (neighbors.size() > 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean inShiftRange(Board board, Coord from, Coord to, int range) {
        Coord diff = new Coord(Math.abs(from.q - to.q), Math.abs(from.r - to.r));
        return diff.q <= range && diff.r <= range;
    }

    public static boolean isSurrounded(Board board, Coord coord, int amount) {
        int count = 0;

        for (Coord neighbor : coord.getNeighborCoords()) {
            Field f = board.get(neighbor);
            if (f.hasStones()) {
                count++;
            }
        }

        if (count >= amount) {
            return true;
        }

        return false;
    }

    public static boolean isSurrounded(Board board, Coord coord) {
        return isSurrounded(board, coord, 5);
    }

    public static boolean hasMovesLeft(Board board, Coord from) {
        for (Map.Entry<Coord, Field> entry : board.getFields().entrySet()) {
            Coord to = entry.getKey();

            if (Move.isValidMove(board, to, from)) {
                return true;
            }
        }

        return false;
    }
}
