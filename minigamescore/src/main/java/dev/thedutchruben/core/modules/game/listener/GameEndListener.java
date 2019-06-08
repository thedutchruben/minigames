package dev.thedutchruben.core.modules.game.listener;

import dev.thedutchruben.core.framework.server.events.GameEndEvent;
import dev.thedutchruben.core.minigamescore;
import dev.thedutchruben.core.utils.BungeeUtil;
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
