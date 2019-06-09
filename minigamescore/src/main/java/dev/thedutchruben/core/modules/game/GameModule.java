package dev.thedutchruben.core.modules.game;

import dev.thedutchruben.core.MiniGamesCore;
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
        pluginManager.registerEvents(new GamePlayerJoinListener(), MiniGamesCore.getInstance());
        pluginManager.registerEvents(new GamePLayerQuitListener(), MiniGamesCore.getInstance());
        pluginManager.registerEvents(new GameEndListener(),MiniGamesCore.getInstance());

    }

    public void registerCommand(){
        MiniGamesCore.getInstance().getCommand("game").setExecutor(new GameCommand());
    }

}
