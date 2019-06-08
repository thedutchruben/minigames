package dev.thedutchruben.core.modules.player.listeners;


import dev.thedutchruben.core.minigamescore;
import dev.thedutchruben.core.framework.player.event.MinigamePlayerJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(null);
        minigamescore.getInstance().getPlayerModule().getPlayerLoader().getMinigamesPlayer(event.getPlayer().getUniqueId()).
                whenComplete((minigamesPlayer, throwable) -> {

            if(throwable != null){
                throwable.printStackTrace();
                event.getPlayer().kickPlayer("");
                return;
            }

            minigamescore.getInstance().getPlayerModule().getMinigamesPlayers().put(event.getPlayer().getUniqueId(),minigamesPlayer);
            Bukkit.getScheduler().runTask(minigamescore.getInstance(),() -> Bukkit.getPluginManager().callEvent(new MinigamePlayerJoinEvent(minigamesPlayer)));

        });

    }
}
