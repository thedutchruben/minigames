package dev.thedutchruben.modules.game.listener;

import dev.thedutchruben.framework.server.events.GameEndEvent;
import dev.thedutchruben.minigamescore;
import dev.thedutchruben.utils.BungeeUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameEndListener implements Listener {


    @EventHandler
    public void onEnd(GameEndEvent gameEndEvent){
        Bukkit.getScheduler().runTaskLater(minigamescore.getInstance(),() -> Bukkit.getOnlinePlayers().forEach(o -> BungeeUtil.send(o,"lobby")),20*50);
        Bukkit.getScheduler().runTaskLater(minigamescore.getInstance(),() -> Bukkit.getServer().spigot().restart(),20*60);
    }
}
