package nl.josaho;

import nl.hanze.hive.Hive;
import nl.josaho.stones.Move;

import java.util.*;

public class Board {
    public HashMap<Coord, Field> fields = new HashMap<Coord, Field>();
    private int stonesPlayed = 0;

    public Board() {
    }

    public HashMap<Coord, Field> getFields() {
        return fields;
    }

    public Field get(Coord coord) {
        if (fields.get(coord) == null) {
            addField(coord);
        }
        return fields.get(coord);
    }

    public void addField(Coord coord, Field field) {
         fields.put(coord, field);
    }

    public void addField(Coord coord) {
        addField(coord, new Field());
    }

    public boolean placeStone(Coord coord, Stone stone) {
        if (fields.get(coord) == null) {
            addField(coord, new Field());
        }

        if (!hasTileBeenPlacedAlready(stone.getTileType(), stone.getColor())) {
            Field field = fields.get(coord);
            field.addStone(stone);
            stonesPlayed++;
            return true;
        }

        return false;
    }

    public void moveStone(Coord from, Coord to) {

        if (fields.get(from) == null) {
            return;
        }

        Field fromField = fields.get(from);
        if (fromField.getStones().length == 0) {
            return;
        }

        if (!Move.isValidMove(this, from, to)) {
            return;
        }

        if (fields.get(to) == null) {
            addField(to);
        }

        Field toField = get(to);
        toField.addStone(fromField.popStone());
        fields.replace(to, get(to), toField);
    }

    public boolean hasTileBeenPlacedAlready(Hive.Tile tile, Hive.Player player) {
        Stone stone = new Stone(player, tile);
        for (Field f: fields.values()) {
            if (f.containsStone(stone)) {
                return true;
            }
        }
        return false;
    }

    public boolean boardIsOneSwarm() {
        ArrayList<Coord> visited = new ArrayList<>();
        Coord start = null;

        for (Map.Entry<Coord, Field> entry : fields.entrySet()) {
            Field field = entry.getValue();
            Coord coord = entry.getKey();
            if (field.hasStones()) {
                start = coord;
                break;
            }
        }

        recursiveSearchConnectedStones(start, visited);

        return visited.size() == stonesPlayed;
    }

    private void recursiveSearchConnectedStones(Coord start, ArrayList<Coord> visited) {
        for (Coord c : start.getNeighborCoords()) {
            Field f = get(c);

            if (!visited.contains(c)) {
                if (f.hasStones()) {
                    visited.add(c);
                    recursiveSearchConnectedStones(c, visited);
                }
            }
        }
    }
}
