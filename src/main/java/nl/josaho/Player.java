package nl.josaho;

import nl.hanze.hive.Hive;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private Hive.Player playerColor;
    private ArrayList<Stone> stones;

    public Player(Hive.Player playerColor) {
        this.playerColor = playerColor;
        this.stones = new ArrayList<>();
        this.stones.add(new Stone(playerColor, Hive.Tile.QUEEN_BEE));
        this.stones.add(new Stone(playerColor, Hive.Tile.SPIDER));
        this.stones.add(new Stone(playerColor, Hive.Tile.SPIDER));
        this.stones.add(new Stone(playerColor, Hive.Tile.BEETLE));
        this.stones.add(new Stone(playerColor, Hive.Tile.BEETLE));
        this.stones.add(new Stone(playerColor, Hive.Tile.SOLDIER_ANT));
        this.stones.add(new Stone(playerColor, Hive.Tile.SOLDIER_ANT));
        this.stones.add(new Stone(playerColor, Hive.Tile.SOLDIER_ANT));
        this.stones.add(new Stone(playerColor, Hive.Tile.GRASSHOPPER));
        this.stones.add(new Stone(playerColor, Hive.Tile.GRASSHOPPER));
        this.stones.add(new Stone(playerColor, Hive.Tile.GRASSHOPPER));
    }

    public ArrayList<Stone> getStones() {
        return stones;
    }

    public int amountOfStonesOfTile(Hive.Tile tile) {
        int amount = 0;
        for (Stone stone: getStones()) {
            if (stone.getTile().equals(tile)) {
                amount++;
            }
        }
        return amount;
    }

    public Hive.Player getPlayerColor() { return playerColor; }

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
        if (stones.size() == 0) {
            return true;
        }
        return false;
    }

    public void playStone(Hive.Tile tile) {
        int i = 0;
        boolean foundStone = false;
        for (Stone s : stones) {
            if (s.getTile().equals(tile)) {
                foundStone = true;
                break;
            }
            i++;
        }
        if (foundStone) {
            stones.remove(i);
        }
    }
}
