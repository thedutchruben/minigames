package dev.thedutchruben.core.modules.logging;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.modules.logging.listeners.PlayerJoinListener;
import dev.thedutchruben.core.modules.logging.listeners.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class LoggingModule {

    public LoggingModule() {
    registerListeners();
    }

    public void registerListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), MiniGamesCore.getInstance());
        pluginManager.registerEvents(new PlayerQuitListener(), MiniGamesCore.getInstance());

    }
}
