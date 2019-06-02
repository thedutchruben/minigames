package dev.thedutchruben.modules.game;

import dev.thedutchruben.minigamescore;
import dev.thedutchruben.modules.game.listener.GameEndListener;
import dev.thedutchruben.modules.game.listener.GamePLayerQuitListener;
import dev.thedutchruben.modules.game.listener.GamePlayerJoinListener;
import dev.thedutchruben.modules.game.runnables.GameStartRunnable;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class GameModule {

    public GameModule(){
        registerListeners();
    }

    public void registerListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new GamePlayerJoinListener(), minigamescore.getInstance());
        pluginManager.registerEvents(new GamePLayerQuitListener(), minigamescore.getInstance());
        pluginManager.registerEvents(new GameEndListener(),minigamescore.getInstance());

    }


    public void forceStart(){
        GameStartRunnable.setTime(10);
        Bukkit.broadcastMessage("De game is geforcestart!");
    }
}
