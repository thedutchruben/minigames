package dev.thedutchruben.framework.player.data;

import dev.thedutchruben.framework.level.Level;
import dev.thedutchruben.framework.player.MinigamesPlayer;
import dev.thedutchruben.framework.player.PlayerData;
import org.bukkit.ChatColor;

import java.util.concurrent.CopyOnWriteArrayList;

public class CommonData  implements PlayerData {

    private long totalPlayTime;
    private Level level;

    public CommonData(long totalPlayTime, Level level) {
        this.totalPlayTime = totalPlayTime;
        this.level = level;
    }

    public static CommonData create(MinigamesPlayer player) {

        return new CommonData(0,Level.getLevels().get(0));
    }

    public long getTotalPlayTime() {
        return totalPlayTime;
    }

    public Level getLevel() {
        return level;
    }
}
