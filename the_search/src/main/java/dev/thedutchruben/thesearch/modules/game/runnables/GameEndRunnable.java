package dev.thedutchruben.thesearch.modules.game.runnables;

import dev.thedutchruben.framework.server.Game;
import dev.thedutchruben.thesearch.Thesearch;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GameEndRunnable implements Runnable {

    private int gameTime = 300;

    @Override
    public void run() {
        int timeleft = (1/ 300) * gameTime;
        Thesearch.getInstance().getGameMode().getBossBar().setProgress(timeleft);
        if(gameTime == 120){
            Bukkit.broadcastMessage("De game eindigd over 2 minuuten!");
        }

        if(gameTime == 60){
            Bukkit.broadcastMessage("De game eindigd over 1 minuut!");
        }

        if(gameTime == 0){
            Bukkit.getPluginManager().callEvent(new dev.thedutchruben.framework.server.events.GameEndEvent(Game.getGame()));
        }
        gameTime--;


    }
}
