package nl.josaho;

import nl.hanze.hive.Hive;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Board {
    public HashMap<Coord, Field> fields = new HashMap<Coord, Field>();

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

    public boolean placeTile(Coord coord, Stone stone) {
        if (fields.get(coord) == null) {
            addField(coord, new Field());
        }

        if (!hasTileBeenPlacedAlready(stone.getTileType(), stone.getColor())) {
            Field field = fields.get(coord);
            field.addStone(stone);
            return true;
        }

        return false;
    }

    public void moveTile(Coord from, Coord to) {
        if (fields.get(from) == null) {
            return;
        }
        Field fromField = fields.get(from);
        if (fromField.getStones().length == 0) {
            return;
        }

        if (fields.get(to) == null) {
            addField(to);
        }
        Field toField = get(to);
        toField.addStone(fromField.popStone());
        fields.replace(to, get(to), toField);
    }

    public void shiftStone(Coord from, Coord to) {
        moveTile(from, to);
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

    public boolean allStonesAreConnected() {
        Coord start = null;
        Stack<Coord> stack = new Stack<>();
        Stack<Coord> visited = new Stack<>();

        for (Map.Entry<Coord, Field> entry : fields.entrySet()) {
            Field field = entry.getValue();
            Coord coord = entry.getKey();
            if (field.hasStones()) {
                start = coord;
                break;
            }
        }

        if (start == null) {
            return true;
        }

        for (Coord c: start.getNeighborCoords()) {
            Field f = get(c);
            if (f != null) {
                if (f.hasStones()) {
                    stack.push(c);
                }
            }
        }

        while (!stack.empty()) {
            Coord c = stack.pop();
            visited.push(c);

            for (Coord cc : c.getNeighborCoords()) {
                if (!visited.contains(cc)) {
                    Field f = get(cc);
                    if (f != null) {
                        if (f.hasStones()) {
                            stack.push(cc);
                        }
                    }
                }
            }
        }

        int check = 0;
        for (Field f: fields.values()) {
            check += f.getStones().length;
        }

        return visited.size() == check;
    }
}
