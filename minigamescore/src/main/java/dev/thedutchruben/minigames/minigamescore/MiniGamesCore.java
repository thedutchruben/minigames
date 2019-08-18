package dev.thedutchruben.minigames.minigamescore;

import org.bukkit.plugin.java.JavaPlugin;

public final class MiniGamesCore extends JavaPlugin {
    private static MiniGamesCore instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MiniGamesCore getInstance() {
        return instance;
    }
}
