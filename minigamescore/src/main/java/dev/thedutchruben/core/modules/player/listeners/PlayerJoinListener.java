package dev.thedutchruben.core.modules.player.listeners;


import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.player.event.MinigamePlayerJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(null);
        MiniGamesCore.getInstance().getPlayerModule().getPlayerLoader().getMinigamesPlayer(event.getPlayer().getUniqueId()).
                whenComplete((miniGamesPlayer, throwable) -> {

            if(throwable != null){
                throwable.printStackTrace();
                event.getPlayer().kickPlayer("");
                return;
            }

                    MiniGamesCore.getInstance().getPlayerModule().getMinigamesPlayers().put(event.getPlayer().getUniqueId(),miniGamesPlayer);
            Bukkit.getScheduler().runTask(MiniGamesCore.getInstance(),() -> Bukkit.getPluginManager().callEvent(new MinigamePlayerJoinEvent(miniGamesPlayer)));

        });

    }
}
