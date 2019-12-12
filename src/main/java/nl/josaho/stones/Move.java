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

        return mini <= maxi;
    }
}
