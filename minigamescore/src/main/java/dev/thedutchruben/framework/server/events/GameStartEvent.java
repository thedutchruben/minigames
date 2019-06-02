package dev.thedutchruben.framework.server.events;

import dev.thedutchruben.framework.server.Game;

/**
 * Get's called when the game starts
 * @author TheDutchRuben
 */
public class GameStartEvent extends GameEvent{
    public GameStartEvent(Game game) {
        super(game);
    }
}
