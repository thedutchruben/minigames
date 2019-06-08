package dev.thedutchruben.core.framework.player.event;

import dev.thedutchruben.core.framework.player.MinigamesPlayer;

public class MinigamePlayerJoinEvent extends PlayerEvent{
    public MinigamePlayerJoinEvent(MinigamesPlayer minigamesPlayer) {
        super(minigamesPlayer);
    }
}
