package dev.thedutchruben.core.modules.game;

import dev.thedutchruben.core.minigamescore;
import dev.thedutchruben.core.modules.game.commands.GameCommand;
import dev.thedutchruben.core.modules.game.listener.GameEndListener;
import dev.thedutchruben.core.modules.game.listener.GamePLayerQuitListener;
import dev.thedutchruben.core.modules.game.listener.GamePlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class GameModule {

    public GameModule(){
        registerListeners();
        registerCommand();
    }

    public void registerListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new GamePlayerJoinListener(), minigamescore.getInstance());
        pluginManager.registerEvents(new GamePLayerQuitListener(), minigamescore.getInstance());
        pluginManager.registerEvents(new GameEndListener(),minigamescore.getInstance());

    }

    public void registerCommand(){
        minigamescore.getInstance().getCommand("game").setExecutor(new GameCommand());
    }

}
