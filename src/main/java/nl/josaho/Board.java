package nl.josaho;

import nl.hanze.hive.Hive;

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

    public boolean placeTile(Coord coord, Stone stone) {
        if (fields.get(coord) == null) {
            addField(coord, new Field());
        }

        if (!hasTileBeenPlacedAlready(stone.getTileType(), stone.getColor())) {
            Field field = fields.get(coord);
            field.addTile(stone);
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

        Field toField = fields.get(to);
        toField.addTile(fromField.popTile());
    }

    public boolean hasTileBeenPlacedAlready(Hive.Tile tile, Hive.Player player) {
        Stone stone = new Stone(player, tile);
        for (Field f: fields.values()) {
            if (f.containsTile(stone)) {
                return true;
            }
        }
        return false;
    }
}
