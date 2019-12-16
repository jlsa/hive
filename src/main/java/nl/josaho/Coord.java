package nl.josaho;

import java.util.Objects;

public class Coord {
    public Integer q = 0;
    public Integer r = 0;

    public Coord(int q, int r) {
        this.q = q;
        this.r = r;
    }

    public Coord[] getNeighborCoords() {
        Coord[] neighbors = new Coord[6];

        neighbors[0] = new Coord(q - 1, r + 1);
        neighbors[1] = new Coord(q + 1, r - 1);
        neighbors[2] = new Coord(q - 1, r);
        neighbors[3] = new Coord(q + 1, r);
        neighbors[4] = new Coord(q, r - 1);
        neighbors[5] = new Coord(q, r + 1);

        return neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return q.equals(coord.q) &&
                r.equals(coord.r);
    }

    @Override
    public int hashCode() {
        return Objects.hash(q, r);
    }

    @Override
    public String toString() {
        return "{ q: " + q + ", r: " + r + " }";
    }
}
