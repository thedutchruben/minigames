package dev.thedutchruben.minigames.minigameslobby;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.model.UpdateOptions;
import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.scoreboard.BPlayerBoard;
import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.core.framework.server.GameState;
import dev.thedutchruben.core.framework.server.GameType;
import dev.thedutchruben.core.utils.Config;
import dev.thedutchruben.minigames.minigameslobby.modules.player.PlayerModule;
import dev.thedutchruben.minigames.minigameslobby.modules.serversigns.ServersignModule;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;


public final class MinigamesLobby extends JavaPlugin {
    private static MinigamesLobby instance;
    private Config lobbyconfig;
    private Config signConfig;
    private ServersignModule serversignModule;
    private PlayerModule playerModule;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        lobbyconfig = new Config(this,"config.yml");
        signConfig = new Config(this,"signs.yml");


        new Game(GameState.LOBBY, GameType.MAIN_LOBBY,0,1000);

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Game.getGame().setContinuGame(true);
        loadLobby();
        playerModule = new PlayerModule();
        serversignModule = new ServersignModule();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadLobby() {
        Game.getGame().setLobby(Bukkit.getWorlds().get(0).getSpawnLocation());
    }


    public static MinigamesLobby getInstance() {
        return instance;
    }

    public Config getLobbyconfig() {
        return lobbyconfig;
    }

    public Config getSignConfig() {
        return signConfig;
    }

    public ServersignModule getServersignModule() {
        return serversignModule;
    }

    public PlayerModule getPlayerModule() {
        return playerModule;
    }




}
