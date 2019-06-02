package dev.thedutchruben.modules.game.listener;

import dev.thedutchruben.framework.player.event.MinigamePlayerQuitEvent;
import dev.thedutchruben.framework.server.Game;
import dev.thedutchruben.minigamescore;
import dev.thedutchruben.modules.game.runnables.GameStartRunnable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GamePLayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(MinigamePlayerQuitEvent event){
        minigamescore.getInstance().getPlayerModule().getMinigamesPlayers().remove(event.getMinigamesPlayer().getUuid());
        if(Game.getGame() != null){
            if(!Game.getGame().isContinuGame()) {
                if (minigamescore.getInstance().getPlayerModule().getMinigamesPlayers().size() <= Game.getGame().getMinPlayers()) {
                    if(Game.getGame().getStartRunnable() != null) {
                        Game.getGame().getStartRunnable().cancel();
                        GameStartRunnable.setTime(120);
                    }
                }
            }
        }
    }
}
