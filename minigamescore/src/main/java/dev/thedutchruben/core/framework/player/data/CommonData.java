package dev.thedutchruben.core.framework.player.data;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.level.Level;
import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import dev.thedutchruben.core.framework.player.PlayerData;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommonData  implements PlayerData {

    private long totalPlayTime;
    private int level;
    private int xp;


    public static CommonData create(MinigamesPlayer player) {
        return new CommonData(0, 1,0);
    }

    public long getTotalPlayTime() {
        return totalPlayTime;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }
}
