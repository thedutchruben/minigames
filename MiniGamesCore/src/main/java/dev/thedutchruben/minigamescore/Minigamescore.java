package dev.thedutchruben.minigamescore;

import dev.thedutchruben.core.gamemode.GameMode;
import dev.thedutchruben.minigamescore.modules.language.LanguageModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Minigamescore extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic

        new LanguageModule();




    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
