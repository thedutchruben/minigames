package dev.thedutchruben.core.framework.server.events;


import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.core.framework.server.GameState;

/**
 * Get's called when the game ends
 * @author TheDutchRuben
 */
public class GameEndEvent extends GameEvent{
    private MinigamesPlayer minigamesPlayer;

    public GameEndEvent(Game game, MinigamesPlayer minigamesPlayer) {
        super(game);
        this.minigamesPlayer = minigamesPlayer;
        game.setGameState(GameState.ENDING);
    }

    public MinigamesPlayer getMinigamesPlayer() {
        return minigamesPlayer;
    }
}
