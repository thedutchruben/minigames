package dev.thedutchruben.thesearch.modules.player.listeners;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.player.event.MinigamePlayerJoinEvent;
import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.thesearch.Thesearch;
import dev.thedutchruben.thesearch.framework.player.SearchPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(MinigamePlayerJoinEvent event){
        event.getMinigamesPlayer().getBukkitPlayer().teleport(Game.getGame().getLobby());
        Thesearch.getInstance().getPlayerModule().getSearchPlayers().put(event.getMinigamesPlayer().getUuid(),new SearchPlayer(event.getMinigamesPlayer().getUuid(),new ArrayList<>()));
        event.getMinigamesPlayer().getBukkitPlayer().setPlayerListHeaderFooter(
                "-------------Minigames-------------\n" +
                        " \n" +
                        "Welkom bij " + Game.getGame().getGameType().getDisplayName(),
                "Game : "+ MiniGamesCore.getInstance().getGameLog().getGameId() +"\n" +
                        "\n" +
                        "-------------Minigames-------------");
    }
}
