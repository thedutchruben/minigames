package dev.thedutchruben.core.framework.level;

import dev.thedutchruben.core.MiniGamesCore;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private int level;
    private int needXp;

    public Level(int level, int needXp) {
        this.level = level;
        this.needXp = needXp;
        MiniGamesCore.getInstance().getLevelLoader().save(this);
    }

    public int getLevel() {
        return level;
    }

    public int getNeedXp() {
        return needXp;
    }

}
