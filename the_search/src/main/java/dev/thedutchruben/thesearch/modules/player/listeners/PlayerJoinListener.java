package dev.thedutchruben.thesearch.modules.player.listeners;

import dev.thedutchruben.framework.player.event.MinigamePlayerJoinEvent;
import dev.thedutchruben.framework.scoreboard.Scoreboard;
import dev.thedutchruben.thesearch.Thesearch;
import dev.thedutchruben.thesearch.framework.player.SearchPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(MinigamePlayerJoinEvent minigamePlayerJoinEvent){
        Thesearch.getInstance().getPlayerModule().getSearchPlayers().put(minigamePlayerJoinEvent.getMinigamesPlayer().getUuid(),new SearchPlayer(minigamePlayerJoinEvent.getMinigamesPlayer().getUuid(),new ArrayList<>()));

    }
}
