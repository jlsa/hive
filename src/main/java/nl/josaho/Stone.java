package nl.josaho;

import nl.hanze.hive.Hive;

import java.util.Arrays;
import java.util.Objects;

public class Stone {
    private Hive.Player playerColor;
    private Stone[] edges = new Stone[6];
    private Hive.Tile tileType;

    public Stone(Hive.Player playerColor) {
        this(playerColor, null);
    }

    public Stone(Hive.Player playerColor, Hive.Tile tileType) {
        this.playerColor = playerColor;
        this.tileType = tileType;
    }

    public Stone(nl.josaho.Player player, Hive.Tile tile) {
        this(player.getPlayerColor(), tile);
    }

    public Hive.Player getColor() {
        return playerColor;
    }

    public Stone[] getEdges() {
        return edges.clone();
    }

    public Hive.Tile getTileType() {
        return tileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stone stone = (Stone) o;
        return playerColor == stone.playerColor &&
                Arrays.equals(edges, stone.edges) &&
                tileType == stone.tileType;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(playerColor, tileType);
        result = 31 * result + Arrays.hashCode(edges);
        return result;
    }
}
