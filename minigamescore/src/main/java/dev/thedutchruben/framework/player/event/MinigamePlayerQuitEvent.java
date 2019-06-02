package dev.thedutchruben.framework.player.event;

import dev.thedutchruben.framework.player.MinigamesPlayer;

public class MinigamePlayerQuitEvent  extends PlayerEvent{
    public MinigamePlayerQuitEvent(MinigamesPlayer minigamesPlayer) {
        super(minigamesPlayer);
    }
}
