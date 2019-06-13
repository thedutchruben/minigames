package dev.thedutchruben.core.modules.level;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.level.Level;
import dev.thedutchruben.core.modules.level.command.LevelCommand;
import dev.thedutchruben.core.modules.level.listeners.XpAddListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class LevelModule {

    public LevelModule() {
        registerListener();
        registerCommand();
    }

    public void registerListener(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new XpAddListener(),MiniGamesCore.getInstance());
    }
    public void registerCommand() {
        MiniGamesCore.getInstance().getCommand("level").setExecutor(new LevelCommand());
    }

    public Level getLevel(int level) {
        for (Level level1 : MiniGamesCore.getInstance().getLevels()) {
            if (level1.getLevel() == level) {
                return level1;
            }
        }
        return null;
    }

}
