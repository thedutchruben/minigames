package dev.thedutchruben.thesearch.modules.game;

import dev.thedutchruben.thesearch.Thesearch;
import dev.thedutchruben.thesearch.framework.player.SearchPlayer;
import dev.thedutchruben.thesearch.modules.game.listeners.GameStartListener;
import dev.thedutchruben.thesearch.modules.game.listeners.HeadPlaceListener;
import dev.thedutchruben.thesearch.modules.game.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameModule {
    private BossBar bossBar = Bukkit.createBossBar(new NamespacedKey(Thesearch.getInstance(),"timer"),"Time left", BarColor.PINK, BarStyle.SOLID);

    public GameModule() {
        registerListeners();
    }

    public void registerListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new HeadPlaceListener(), Thesearch.getInstance());
        pluginManager.registerEvents(new GameStartListener(), Thesearch.getInstance());
        pluginManager.registerEvents(new PlayerInteractListener(), Thesearch.getInstance());
    }



    public Iterator<SearchPlayer> getTopPlayers(){
       return Thesearch.getInstance().getPlayerModule().getSearchPlayers().values().stream().
               limit(5).
                sorted((o1, o2) -> Integer.valueOf(o1.getLocations().size()).compareTo(Integer.valueOf(o2.getLocations().size()))).iterator();
    }



    public BossBar getBossBar() {
        return bossBar;
    }
}
