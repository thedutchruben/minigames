package dev.thedutchruben.core.framework.player.data;

import dev.thedutchruben.core.framework.level.Level;
import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import dev.thedutchruben.core.framework.player.PlayerData;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class TheSearchData  implements PlayerData {

    private Date lastTimePlayed;
    private int gamesPlayed;
    private int totalHeadsFound;
    private int gamesWon;

    public static TheSearchData create(MinigamesPlayer player) {
        return new TheSearchData(new Date(),0,0,0);
    }

    public Date getLastTimePlayed() {
        return lastTimePlayed;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getTotalHeadsFound() {
        return totalHeadsFound;
    }

    public int getGamesWon() {
        return gamesWon;
    }
}

