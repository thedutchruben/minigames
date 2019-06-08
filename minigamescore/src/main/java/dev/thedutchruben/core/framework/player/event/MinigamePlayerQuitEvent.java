package dev.thedutchruben.core.framework.player.event;

import dev.thedutchruben.core.framework.player.MinigamesPlayer;

public class MinigamePlayerQuitEvent  extends PlayerEvent{
    public MinigamePlayerQuitEvent(MinigamesPlayer minigamesPlayer) {
        super(minigamesPlayer);
    }
}
