package nl.josaho;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

public class Field {
    private Field[] neighbors;
    private Stack<Stone> stones;

    public Field() {
        neighbors = new Field[6];
        stones = new Stack<Stone>();
    }

    public Field[] getNeighbors() {
        return neighbors;
    }

    public Stone[] getStones() {
        return this.stones.toArray(new Stone[this.stones.size()]);
    }

    public boolean hasStones() {
        return this.stones.size() > 0;
    }


    public void addTile(Stone stone) {
        if (!this.stones.contains(stone)) {
            this.stones.push(stone);
        }
    }

    public Stone popTile()
    {
        return this.stones.pop();
    }

    public Stone peekTile() { return this.stones.peek(); }

    public boolean containsTile(Stone stone) {
        return this.stones.contains(stone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return Arrays.equals(neighbors, field.neighbors) &&
                Objects.equals(stones, field.stones);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(stones);
        result = 31 * result + Arrays.hashCode(neighbors);
        return result;
    }
}
