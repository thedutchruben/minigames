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
        Bukkit.getScheduler().runTaskLater(MiniGamesCore.getInstance(),() ->{
            MiniGamesCore.getInstance().getPlayerModule().getMinigamesPlayers().values().forEach(minigamesPlayer -> {
                MiniGamesCore.getInstance().getPlayerModule().getPlayerLoader().save(minigamesPlayer).whenComplete((aVoid, throwable) -> {
                    BungeeUtil.send(minigamesPlayer.getBukkitPlayer(),"lobby");
                });

            });
        },20*40);
        Bukkit.getScheduler().runTaskLater(MiniGamesCore.getInstance(),() -> Bukkit.getServer().shutdown(),20*60);
    }
}
