package dev.thedutchruben.core.framework.server;

/**
 * Game type of the server
 * @author TheDutchRuben
 */
public enum  GameType {
    MAIN_LOBBY("&2Lobby"),
    TNT_RUN("&4Tnt Run"),
    BOW_SPLEEF("&4Bow Spleef"),
    TNT_TAG("&4Tnt Tag"),
    HIDE_AND_SEEK("&6Hide and Seek"),
    THE_SEARCH("&7The Search"),
    PARKOUR("&4Tnt Run"),
    BINGO("&4Tnt Run"),
    KIT_PVP("&4Tnt Run"),
    TASKING("&4Tnt Run"),
    TEAM_DEATH_MATCH("&4Tnt Run"),
    RACIN("&4Tnt Run");

    private String displayName;
    GameType(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
