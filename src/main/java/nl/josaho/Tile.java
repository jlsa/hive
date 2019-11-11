package nl.josaho;

import nl.hanze.hive.IHive.*;

import java.util.Arrays;
import java.util.Objects;

public class Tile {
    private PlayerColor playerColor;
    private Tile[] edges = new Tile[6];
    private TileType tileType;

    public Tile(PlayerColor playerColor) {
        this(playerColor, null);
    }

    public Tile(PlayerColor playerColor, TileType tileType) {
        this.playerColor = playerColor;
        this.tileType = tileType;
    }

    public PlayerColor getColor() {
        return playerColor;
    }

    public Tile[] getEdges() {
        return edges.clone();
    }

    public TileType getTileType() {
        return tileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return playerColor == tile.playerColor &&
                Arrays.equals(edges, tile.edges) &&
                tileType == tile.tileType;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(playerColor, tileType);
        result = 31 * result + Arrays.hashCode(edges);
        return result;
    }
}
