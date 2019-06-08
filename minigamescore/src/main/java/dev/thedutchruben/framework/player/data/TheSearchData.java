package dev.thedutchruben.framework.player.data;

import dev.thedutchruben.framework.player.PlayerData;

import java.util.Date;

public class TheSearchData  implements PlayerData {

    private Date lastTimePlayed;
    private int gamesPlayed;
    private int totalHeadsFound;
    private int gamesWon;

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

