package dev.thedutchruben.minigames.minigameslobby.framework.scoreboard;

import dev.thedutchruben.core.framework.scoreboard.BPlayerBoard;
import dev.thedutchruben.core.framework.scoreboard.PlayerBoard;
import org.bukkit.Material;


public class LocationBoard {
    private String name;
    private Material material;
    private dev.thedutchruben.core.framework.scoreboard.BPlayerBoard scoreboard;

    public LocationBoard(String name, Material material, BPlayerBoard scoreboard) {
        this.name = name;
        this.material = material;
        this.scoreboard = scoreboard;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public BPlayerBoard getScoreboard() {
        return scoreboard;
    }
}
