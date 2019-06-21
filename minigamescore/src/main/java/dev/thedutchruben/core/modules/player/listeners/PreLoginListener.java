package dev.thedutchruben.core.modules.player.listeners;

import dev.thedutchruben.core.framework.server.Game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PreLoginListener implements Listener {

    @EventHandler
    public void onPreJoin(AsyncPlayerPreLoginEvent event){
        if (Bukkit.getOnlinePlayers().size() <= Game.getGame().getMaxPlayers()) {
            return;
        }else{
            event.setKickMessage(ChatColor.RED + "Game is full");
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_FULL);
        }
    }
}
