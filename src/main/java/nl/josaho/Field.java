package nl.josaho;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

public class Field {
    private Field[] neighbors;
    private Stack<Tile> tiles;

    public Field() {
        neighbors = new Field[6];
        tiles = new Stack<Tile>();
    }

    public Field[] getNeighbors() {
        return neighbors;
    }

    public Tile[] getTiles() {
        return this.tiles.toArray(new Tile[this.tiles.size()]);
    }

    public void addTile(Tile tile) {
        if (!this.tiles.contains(tile)) {
            this.tiles.push(tile);
        }
    }

    public Tile popTile()
    {
        return this.tiles.pop();
    }

    public Tile peekTile() { return this.tiles.peek(); }

    public boolean containsTile(Tile tile) {
        return this.tiles.contains(tile);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return Arrays.equals(neighbors, field.neighbors) &&
                Objects.equals(tiles, field.tiles);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(tiles);
        result = 31 * result + Arrays.hashCode(neighbors);
        return result;
    }
}
