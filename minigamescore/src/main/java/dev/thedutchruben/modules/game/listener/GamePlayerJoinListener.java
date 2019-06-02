package dev.thedutchruben.modules.game.listener;

import dev.thedutchruben.framework.player.event.MinigamePlayerJoinEvent;
import dev.thedutchruben.framework.server.Game;
import dev.thedutchruben.framework.server.GameState;
import dev.thedutchruben.minigamescore;
import dev.thedutchruben.modules.game.runnables.GameStartRunnable;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GamePlayerJoinListener implements Listener {


    @EventHandler
    public void onJoin(MinigamePlayerJoinEvent event){
        if(Game.getGame() != null){
            if(!Game.getGame().isContinuGame()) {
                if (minigamescore.getInstance().getPlayerModule().getMinigamesPlayers().size() == Game.getGame().getMinPlayers()) {
                    //start timer
                    Game.getGame().setStartRunnable(Bukkit.getScheduler().runTaskTimerAsynchronously(minigamescore.getInstance(), new GameStartRunnable(), 20L, 20L));
                    Game.getGame().setGameState(GameState.STARTING);

                }

            }
            if(Game.getGame().getLobby() != null){
                event.getMinigamesPlayer().getBukkitPLayer().teleport(Game.getGame().getLobby());
            }
        }
    }


}
