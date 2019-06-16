package dev.thedutchruben.core.modules.game.listener;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.player.event.MinigamePlayerJoinEvent;
import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.core.framework.server.GameState;
import dev.thedutchruben.core.modules.game.runnables.GameStartRunnable;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GamePlayerJoinListener implements Listener {


    @EventHandler
    public void onJoin(MinigamePlayerJoinEvent event){
        if(Game.getGame() != null){
            if(!Game.getGame().isContinuGame()) {
                if (MiniGamesCore.getInstance().getPlayerModule().getMinigamesPlayers().size() == Game.getGame().getMinPlayers()) {
                    //start timer
                    Game.getGame().setStartRunnable(Bukkit.getScheduler().runTaskTimerAsynchronously(MiniGamesCore.getInstance(), new GameStartRunnable(), 20L, 20L));
                    Game.getGame().setGameState(GameState.STARTING);

                }

            }
            if(Game.getGame().getLobby() != null){
                event.getMinigamesPlayer().getBukkitPlayer().teleport(Game.getGame().getLobby());
            }
        }
    }


}
