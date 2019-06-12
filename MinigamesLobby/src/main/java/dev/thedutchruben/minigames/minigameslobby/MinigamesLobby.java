package dev.thedutchruben.minigames.minigameslobby;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.model.UpdateOptions;
import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.core.framework.server.GameState;
import dev.thedutchruben.core.framework.server.GameType;
import dev.thedutchruben.core.utils.Scoreboard;
import dev.thedutchruben.minigames.minigameslobby.framework.scoreboard.LocationBoard;
import dev.thedutchruben.minigames.minigameslobby.framework.serversign.ServerSign;
import dev.thedutchruben.minigames.minigameslobby.modules.player.PlayerModule;
import dev.thedutchruben.minigames.minigameslobby.modules.serversigns.ServersignModule;
import dev.thedutchruben.core.utils.Config;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;


public final class MinigamesLobby extends JavaPlugin {
    private static MinigamesLobby instance;
    private Config lobbyconfig;
    private Config signConfig;
    private List<LocationBoard> scoreboards;
    private ServersignModule serversignModule;
    private PlayerModule playerModule;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        lobbyconfig = new Config(this,"config.yml");
        signConfig = new Config(this,"signs.yml");

        try {
            new Game(InetAddress.getLocalHost().getHostName(),GameState.LOBBY, GameType.MAIN_LOBBY,0,1000);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Game.getGame().setContinuGame(true);
        loadLobby();
        playerModule = new PlayerModule();
        serversignModule = new ServersignModule();
        scoreboards = getScoreboards();
        Scoreboard scoreboard = new Scoreboard("SCOREBOARD");

        saveScoreboard(new LocationBoard("DefaultBoard", Material.AIR,scoreboard));
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

    public List<LocationBoard> getScoreboards() {
        List<LocationBoard> lockedBlocks = new CopyOnWriteArrayList<>();
        MiniGamesCore.getInstance().getMongoDb().getMongoDatabase().getCollection("lobby-scoreboard").find().forEach((Block<Document>) document -> {
            lockedBlocks.add(MiniGamesCore.getInstance().getMongoDb().getGson().fromJson(document.toJson(), LocationBoard.class));
        });

        return lockedBlocks;
    }

    public void saveScoreboard(LocationBoard serverSign){
        CompletableFuture.runAsync(() -> {
            Document document = Document.parse(MiniGamesCore.getInstance().getMongoDb().getGson().toJson(serverSign, LocationBoard.class)).append("_id",serverSign.getName());
            MiniGamesCore.getInstance().getMongoDb().getMongoDatabase().getCollection("lobby-scoreboard").replaceOne(new BasicDBObject().append("_id", UUID.randomUUID().toString()), document, new UpdateOptions().upsert(true));
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }

}
