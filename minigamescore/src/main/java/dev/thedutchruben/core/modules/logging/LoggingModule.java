package dev.thedutchruben.core.modules.logging;

import dev.thedutchruben.core.minigamescore;
import dev.thedutchruben.core.modules.logging.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class LoggingModule {

    public LoggingModule() {
    registerListeners();
    }

    public void registerListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), minigamescore.getInstance());
    }
}
