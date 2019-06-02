package dev.thedutchruben.modules.player.listeners;

import dev.thedutchruben.framework.player.event.MinigamePlayerQuitEvent;
import dev.thedutchruben.minigamescore;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.setQuitMessage(null);
        minigamescore.getInstance().getPlayerModule().getPlayerLoader().save(minigamescore.getInstance().getPlayerModule().getMinigamesPlayers().get(event.getPlayer().getUniqueId())).whenComplete((aVoid, throwable) -> {
            if(throwable != null){
                throwable.printStackTrace();
            }
            event.setQuitMessage(null);
            Bukkit.getPluginManager().callEvent(new MinigamePlayerQuitEvent((minigamescore.getInstance().getPlayerModule().getMinigamesPlayers().get(event.getPlayer().getUniqueId()))));
        });
    }
}
