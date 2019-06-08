package dev.thedutchruben.tntrun;

import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.core.framework.server.GameState;
import dev.thedutchruben.core.framework.server.GameType;
import org.bukkit.plugin.java.JavaPlugin;

public final class Tntrun extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        new Game(GameState.LOBBY, GameType.TNT_RUN,2,10);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
