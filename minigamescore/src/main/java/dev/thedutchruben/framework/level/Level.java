package dev.thedutchruben.framework.level;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private static List<Level> levels = new ArrayList<>();

    private int level;
    private int needXp;

    public Level(int level, int needXp) {
        this.level = level;
        this.needXp = needXp;
        levels.add(this);
    }

    public int getLevel() {
        return level;
    }

    public int getNeedXp() {
        return needXp;
    }

    public static List<Level> getLevels() {
        return levels;
    }
}
