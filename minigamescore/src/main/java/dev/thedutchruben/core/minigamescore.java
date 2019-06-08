package dev.thedutchruben.core;

import dev.thedutchruben.core.utils.BungeeUtil;
import dev.thedutchruben.core.framework.database.MongoDb;
import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.core.framework.server.GameLog;
import dev.thedutchruben.core.framework.server.GameState;
import dev.thedutchruben.core.framework.server.Log;
import dev.thedutchruben.core.modules.game.GameModule;
import dev.thedutchruben.core.modules.logging.LoggingModule;
import dev.thedutchruben.core.modules.player.PlayerModule;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Date;

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
    @Getter private GameLog gameLog;
    /**
     * Enabling of the plugin
     * The modules enables one for one
     * if one of the modules start not good the server will shutdown
     */
    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            instance = this;
            mongoDb = new MongoDb();
            playerModule = new PlayerModule();
            gameModule = new GameModule();
            new LoggingModule();
            BungeeUtil.registerBungeeCordOut(this);
            gameLog = new GameLog(new ArrayList<>());
            gameLog.addEvent(new Log(new Date(),false,"Server is opgestart!"));
        }catch (Exception exception){
            exception.printStackTrace();
            Bukkit.shutdown();
        }

    }

    /**
     * Disabling the plugin
     * the plugin wil stop one for one the modules
     */
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
