package dev.thedutchruben.core.framework.player.data;

import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import dev.thedutchruben.core.framework.player.PlayerData;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommonData  implements PlayerData {

    private long totalPlayTime;
    private int level;
    private double xp;


    public static CommonData create(MinigamesPlayer player) {
        return new CommonData(0, 1,0);
    }

    public long getTotalPlayTime() {
        return totalPlayTime;
    }

    public int getLevel() {
        return level;
    }

    public double getXp() {
        return xp;
    }

    public void addXp(double xp){
        this.xp += xp;
    }
}
