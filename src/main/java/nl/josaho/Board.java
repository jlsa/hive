package nl.josaho;

import java.util.HashMap;

public class Board {
    public HashMap<Coord, Field> fields = new HashMap<Coord, Field>();

    public Board() {
    }

    public HashMap<Coord, Field> getFields() {
        return fields;
    }

    public void addField(Coord coord, Field field) {
         fields.put(coord, field);
    }

    public boolean placeTile(Coord coord, Tile tile) {
        if (fields.get(coord) == null) {
            addField(coord, new Field());
        }

        if (!hasTileBeenPlacedAlready(tile)) {
            Field field = fields.get(coord);
            field.addTile(tile);
            return true;
        }

        return false;
    }

    public void moveTile(Coord from, Coord to) {
        if (fields.get(from) == null) {
            return;
        }
        Field fromField = fields.get(from);
        if (fromField.getTiles().length == 0) {
            return;
        }

        Field toField = fields.get(to);
        toField.addTile(fromField.popTile());
    }

    public boolean hasTileBeenPlacedAlready(Tile tile) {
        for (Field f: fields.values()) {
            if (f.containsTile(tile)) {
                return true;
            }
        }
        return false;
    }
}
