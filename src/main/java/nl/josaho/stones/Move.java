package nl.josaho.stones;

import nl.hanze.hive.Hive;
import nl.josaho.Board;
import nl.josaho.Coord;
import nl.josaho.Field;
import nl.josaho.Stone;

import java.util.ArrayList;

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
        int mini = -1;
        int maxi = -1;

        Field a = board.get(from);
        Field b = board.get(to);
        // min(h(n1), h(n2)) <= max(h(a) - 1, h(b))

        ArrayList<Coord> neighbors = new ArrayList<>();

        for (Coord neighborA : from.getNeighborCoords())
        {
            for (Coord neighborB : to.getNeighborCoords()) {
                if (neighborA.equals(neighborB)) {
                    neighbors.add(neighborA);
                }
            }
        }
        if (neighbors.size() >= 2) {
            Coord n1 = neighbors.get(0);
            Coord n2 = neighbors.get(1);

            Field fieldN1 = board.get(n1);
            Field fieldN2 = board.get(n2);

            mini = Math.min(fieldN1.height(), fieldN2.height());
            maxi = Math.max(a.height() - 1, b.height());
        }


        return mini <= maxi;
    }
}
