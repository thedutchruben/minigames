package dev.thedutchruben.core.modules.game.runnables;

import dev.thedutchruben.core.minigamescore;
import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.core.framework.server.GameState;
import dev.thedutchruben.core.framework.server.events.GameStartEvent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartRunnable extends BukkitRunnable {

    private static int sec = 20;

    @Override
    public void run() {
        if(sec == 1){
            Bukkit.broadcastMessage("De game start over " + sec + " seconde!");
            Bukkit.getOnlinePlayers().forEach(o -> o.sendTitle("De game start over " ,sec + " seconde!"));
        }

        if(sec >=  1 && sec <= 10 || sec == 20 || sec == 30) {
            Bukkit.broadcastMessage("De game start over " + sec + " seconden!");
            Bukkit.getOnlinePlayers().forEach(o -> o.sendTitle("De game start over " ,sec + " seconden!"));
        }

        if(sec == 0){
            Bukkit.getScheduler().runTask(minigamescore.getInstance(),() -> {
                Bukkit.getServer().getPluginManager().callEvent(new GameStartEvent(Game.getGame()));
                Game.getGame().setGameState(GameState.INGAME);
            });

        }

        sec--;
    }

    public static void setTime(int time){
        sec = time;
    }
}
