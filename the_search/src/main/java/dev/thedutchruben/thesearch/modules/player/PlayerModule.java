package dev.thedutchruben.thesearch.modules.player;

import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import dev.thedutchruben.thesearch.Thesearch;
import dev.thedutchruben.thesearch.framework.player.SearchPlayer;
import dev.thedutchruben.thesearch.modules.player.listeners.PlayerJoinListener;
import dev.thedutchruben.thesearch.modules.player.listeners.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerModule {

    private Map<UUID,SearchPlayer> searchPlayers;

    public PlayerModule() {
        searchPlayers = new HashMap<>();
        registerListeners();
    }

    public void registerListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), Thesearch.getInstance());
        pluginManager.registerEvents(new PlayerQuitListener(), Thesearch.getInstance());
    }

    public  Map<UUID,SearchPlayer> getSearchPlayers() {
        return searchPlayers;
    }

    public void setScoreboard(MinigamesPlayer minigamesPlayer){

    }
}
