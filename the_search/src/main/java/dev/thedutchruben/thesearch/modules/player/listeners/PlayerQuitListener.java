package dev.thedutchruben.thesearch.modules.player.listeners;

import dev.thedutchruben.framework.player.event.MinigamePlayerQuitEvent;
import dev.thedutchruben.thesearch.Thesearch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(MinigamePlayerQuitEvent minigamePlayerQuitEvent){
        Thesearch.getInstance().getPlayerModule().getSearchPlayers().remove(minigamePlayerQuitEvent.getMinigamesPlayer().getUuid());
    }
}
