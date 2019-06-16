package dev.thedutchruben.core.modules.game.listener;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.server.events.GameEndEvent;
import dev.thedutchruben.core.utils.BungeeUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameEndListener implements Listener {


    @EventHandler
    public void onEnd(GameEndEvent gameEndEvent){
        Bukkit.getScheduler().runTaskLater(MiniGamesCore.getInstance(),() -> Bukkit.getOnlinePlayers().forEach(o -> BungeeUtil.send(o,"lobby")),20*50);
        Bukkit.getScheduler().runTaskLater(MiniGamesCore.getInstance(),() -> Bukkit.getServer().shutdown(),20*60);
    }
}
