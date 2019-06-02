package dev.thedutchruben.framework.server.events;

import dev.thedutchruben.framework.server.Game;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Basic data for the gameEvent's
 * @author TheDutchRuben
 */

public class GameEvent extends Event {
    private static final HandlerList handler = new HandlerList();

    @Getter
    private final Game game;

    public GameEvent(Game game) {
        this.game = game;
    }

    public static HandlerList getHandlerList() {
        return handler;
    }

    @Override
    public HandlerList getHandlers() {
        return handler;
    }
}
