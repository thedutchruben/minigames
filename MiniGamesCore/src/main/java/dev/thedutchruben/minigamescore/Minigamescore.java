package dev.thedutchruben.minigamescore;

import dev.thedutchruben.minigamescore.modules.language.LanguageModule;
import org.bukkit.plugin.java.JavaPlugin;

public final class Minigamescore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new LanguageModule();
        LanguageModule.getLanguages().forEach(s -> {
            System.out.println("DEBUG : " + LanguageModule.translate("Server started", s));
        });


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
