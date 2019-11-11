package nl.josaho;

import nl.hanze.hive.IHive.*;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private PlayerColor playerColor;
    private ArrayList<Tile> tiles;

    public Player(PlayerColor playerColor) {
        this.playerColor = playerColor;
        this.tiles = new ArrayList<>();
        this.tiles.add(new Tile(playerColor, TileType.QUEEN_BEE));
        this.tiles.add(new Tile(playerColor, TileType.SPIDER));
        this.tiles.add(new Tile(playerColor, TileType.SPIDER));
        this.tiles.add(new Tile(playerColor, TileType.BEETLE));
        this.tiles.add(new Tile(playerColor, TileType.BEETLE));
        this.tiles.add(new Tile(playerColor, TileType.SOLDIER_ANT));
        this.tiles.add(new Tile(playerColor, TileType.SOLDIER_ANT));
        this.tiles.add(new Tile(playerColor, TileType.SOLDIER_ANT));
        this.tiles.add(new Tile(playerColor, TileType.GRASSHOPPER));
        this.tiles.add(new Tile(playerColor, TileType.GRASSHOPPER));
        this.tiles.add(new Tile(playerColor, TileType.GRASSHOPPER));
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public PlayerColor getPlayerColor() { return playerColor; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerColor == player.playerColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerColor);
    }

    public boolean canPass() {
        return false;
    }
}
