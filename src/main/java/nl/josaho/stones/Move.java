package nl.josaho.stones;

import nl.hanze.hive.Hive;
import nl.josaho.Board;
import nl.josaho.Coord;
import nl.josaho.Field;
import nl.josaho.Stone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Move {

    public static Board Shift(Board board, Coord from, Coord to) {

        return board;
    }

    public static boolean isValidMove(Board board, Coord from, Coord to) {
        Stone stone = board.get(from).peekStone();

        if (stone.getTileType().equals(Hive.Tile.QUEEN_BEE)) {
            return QueenBeeMove.isMoveAllowed(board, from, to);
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

        boolean isInShiftRange = inShiftRange(board, from, to);
        boolean connectedDuringShifting = isShiftConnected(board, from, to);
        return (mini <= maxi) && isInShiftRange && connectedDuringShifting;
    }

    public static boolean inShiftRange(Board board, Coord from, Coord to) {
        // check for distance, if its bigger than one always fail
        Coord diff = new Coord(Math.abs(from.q - to.q), Math.abs(from.r - to.r));
        System.out.println("diff: " + diff.toString());
        return diff.q > 1 || diff.r > 1;
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
}
