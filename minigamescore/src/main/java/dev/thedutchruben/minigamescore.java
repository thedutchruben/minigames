package dev.thedutchruben;

import dev.thedutchruben.framework.database.MongoDb;
import dev.thedutchruben.framework.server.Game;
import dev.thedutchruben.framework.server.GameState;
import dev.thedutchruben.modules.game.GameModule;
import dev.thedutchruben.modules.player.PlayerModule;
import dev.thedutchruben.utils.BungeeUtil;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *  The main class of the plugin
 *  This class registers the modules
 * @author TheDutchRuben
 **/
public final class minigamescore extends JavaPlugin {
    @Getter private static minigamescore instance;
    @Getter private PlayerModule playerModule;
    @Getter private GameModule gameModule;
    @Getter private MongoDb mongoDb;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        mongoDb = new MongoDb();
        playerModule = new PlayerModule();
        gameModule = new GameModule();
        BungeeUtil.registerBungeeCordOut(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Game.getGame().setMaxPlayers(0);
        Game.getGame().setGameState(GameState.RESTARTING);

        gameModule = null;
        playerModule = null;
        mongoDb = null;
        instance = null;
    }
}
