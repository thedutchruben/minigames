package dev.thedutchruben.core.modules.logging.listeners;

import dev.thedutchruben.core.framework.server.Log;
import dev.thedutchruben.core.minigamescore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Date;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        minigamescore.getInstance().getGameLog().addEvent(new Log(new Date(),true,event.getPlayer().getName() + " joined the game!"));
    }
}
