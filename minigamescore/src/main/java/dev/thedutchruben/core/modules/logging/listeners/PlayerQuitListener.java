package dev.thedutchruben.core.modules.logging.listeners;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.server.Log;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Date;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        MiniGamesCore.getInstance().getGameLog().addEvent(new Log(new Date(),true,event.getPlayer().getName() + " quit the game!"));
    }
}
