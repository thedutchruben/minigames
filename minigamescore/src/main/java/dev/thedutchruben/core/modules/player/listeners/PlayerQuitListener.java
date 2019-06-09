package dev.thedutchruben.core.modules.player.listeners;

import dev.thedutchruben.core.framework.player.event.MinigamePlayerQuitEvent;
import dev.thedutchruben.core.MiniGamesCore;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.setQuitMessage(null);
        MiniGamesCore.getInstance().getPlayerModule().getPlayerLoader().save(MiniGamesCore.getInstance().getPlayerModule().getMinigamesPlayers().get(event.getPlayer().getUniqueId())).whenComplete((aVoid, throwable) -> {
            if(throwable != null){
                throwable.printStackTrace();
            }
            event.setQuitMessage(null);
            Bukkit.getPluginManager().callEvent(new MinigamePlayerQuitEvent((MiniGamesCore.getInstance().getPlayerModule().getMinigamesPlayers().get(event.getPlayer().getUniqueId()))));
        });
    }
}
