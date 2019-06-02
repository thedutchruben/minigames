package dev.thedutchruben.minigames.minigameslobby.modules.serversigns.listeners;

import dev.thedutchruben.framework.server.GameType;
import dev.thedutchruben.minigames.minigameslobby.framework.serversign.ServerSign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignCreateListener implements Listener {

    @EventHandler
    public void onCreate(SignChangeEvent signChangeEvent){
        new ServerSign(signChangeEvent.getBlock().getLocation(), GameType.valueOf(signChangeEvent.getLine(0)));

    }
}
