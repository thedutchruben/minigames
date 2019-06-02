package dev.thedutchruben.framework.player.event;

import dev.thedutchruben.framework.player.MinigamesPlayer;

public class MinigamePlayerJoinEvent extends PlayerEvent{
    public MinigamePlayerJoinEvent(MinigamesPlayer minigamesPlayer) {
        super(minigamesPlayer);
    }
}
