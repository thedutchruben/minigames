package dev.thedutchruben.minigames.minigameslobby.framework.scoreboard;

import dev.thedutchruben.core.utils.Scoreboard;
import org.bukkit.Material;


public class LocationBoard {
    private String name;
    private Material material;
    private Scoreboard scoreboard;

    public LocationBoard(String name, Material material, Scoreboard scoreboard) {
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

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
