package dev.thedutchruben.thesearch.modules.game.runnables;

import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.thesearch.Thesearch;
import org.bukkit.Bukkit;

public class GameEndRunnable implements Runnable {

    private int gameTime = 300;

    @Override
    public void run() {
        float timeleft = (1/ 300);
        System.out.println(timeleft * gameTime);
        Thesearch.getInstance().getGameMode().getBossBar().setProgress(timeleft * gameTime);
        if(gameTime == 120){
            Bukkit.broadcastMessage("De game eindigd over 2 minuuten!");
        }

        if(gameTime == 60){
            Bukkit.broadcastMessage("De game eindigd over 1 minuut!");
        }

        if(gameTime == 0){
            Bukkit.getScheduler().runTask(Thesearch.getInstance(),() -> Bukkit.getPluginManager().callEvent(new dev.thedutchruben.core.framework.server.events.GameEndEvent(Game.getGame())));
        }
        gameTime--;


    }
}
