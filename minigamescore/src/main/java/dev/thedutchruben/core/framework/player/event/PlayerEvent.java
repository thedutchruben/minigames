package dev.thedutchruben.core.framework.player.event;

import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerEvent extends Event {
    private static final HandlerList handler = new HandlerList();

    @Getter
    private final MinigamesPlayer minigamesPlayer;

    public PlayerEvent(MinigamesPlayer minigamesPlayer) {
        this.minigamesPlayer = minigamesPlayer;
    }

    public static HandlerList getHandlerList() {
        return handler;
    }

    @Override
    public HandlerList getHandlers() {
        return handler;
    }
}
