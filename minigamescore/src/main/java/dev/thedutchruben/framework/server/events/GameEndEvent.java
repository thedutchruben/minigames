package dev.thedutchruben.framework.server.events;


import dev.thedutchruben.framework.server.Game;
import dev.thedutchruben.framework.server.GameState;

/**
 * Get's called when the game ends
 * @author TheDutchRuben
 */
public class GameEndEvent extends GameEvent{


    public GameEndEvent(Game game) {
        super(game);
        game.setGameState(GameState.ENDING);
    }
}
