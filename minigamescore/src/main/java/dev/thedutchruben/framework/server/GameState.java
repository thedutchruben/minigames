package dev.thedutchruben.framework.server;

/**
 * GameState of the server
 * @author TheDutchRuben
 */

public enum  GameState {
    LOBBY("&2Lobby",true),
    STARTING("&2Starting",true),
    INGAME("&4Started",false),
    ENDING("&cEnding",false),
    RESTARTING("&4Restarting",false);

    private String display;
    private boolean canJoin;
    GameState(String displayState,boolean canJoin){
        this.display = displayState;
        this.canJoin = canJoin;
    }

    public String getDisplay() {
        return display;
    }

    public boolean isCanJoin() {
        return canJoin;
    }
}
