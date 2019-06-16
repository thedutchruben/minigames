package dev.thedutchruben.minigames.minigameslobby.modules.serversigns;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.core.framework.server.GameState;
import dev.thedutchruben.core.framework.server.GameType;
import dev.thedutchruben.minigames.minigameslobby.MinigamesLobby;
import dev.thedutchruben.minigames.minigameslobby.framework.serversign.ServerSign;
import dev.thedutchruben.minigames.minigameslobby.modules.serversigns.listeners.SignClickListener;
import dev.thedutchruben.minigames.minigameslobby.modules.serversigns.listeners.SignCreateListener;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServersignModule {

    private List<Game> games;
    private List<ServerSign> serverSigns;
    private MongoCollection mongoCollection;
    private Map<Location,Game> locationGameMap;
    public ServersignModule() {
        mongoCollection = MiniGamesCore.getInstance().getMongoDb().getMongoDatabase().getCollection("servers");
        locationGameMap = new HashMap<>();
        registerListeners();
        Bukkit.getScheduler().runTaskTimerAsynchronously(MinigamesLobby.getInstance(),() ->{
            games = getGames();
            serverSigns = getGamesSignDb();
            checkSigns();
        },0,20);
    }

    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new SignClickListener(),MinigamesLobby.getInstance());
        Bukkit.getPluginManager().registerEvents(new SignCreateListener(),MinigamesLobby.getInstance());
    }
    public List<Game> getGames() {
        List<Game> lockedBlocks = new CopyOnWriteArrayList<>();
        mongoCollection.find().forEach((Block<Document>) document -> {
            lockedBlocks.add(MiniGamesCore.getInstance().getMongoDb().getGson().fromJson(document.toJson(), Game.class));
        });

        return lockedBlocks;
    }

    public List<ServerSign> getGamesSignDb() {
        List<ServerSign> lockedBlocks = new CopyOnWriteArrayList<>();
        MiniGamesCore.getInstance().getMongoDb().getMongoDatabase().getCollection("server_signs").find().forEach((Block<Document>) document -> {
            lockedBlocks.add(MiniGamesCore.getInstance().getMongoDb().getGson().fromJson(document.toJson(), ServerSign.class));
        });

        return lockedBlocks;
    }

    public void saveGameSign(ServerSign serverSign){
        CompletableFuture.runAsync(() -> {
            Document document = Document.parse(MiniGamesCore.getInstance().getMongoDb().getGson().toJson(serverSign, ServerSign.class));
            MiniGamesCore.getInstance().getMongoDb().getMongoDatabase().getCollection("server_signs").replaceOne(new BasicDBObject().append("_id", UUID.randomUUID().toString()), document, new UpdateOptions().upsert(true));
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }


    public List<ServerSign> getServerSigns() {
        return serverSigns;
    }

    public MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public void setServerSigns(List<ServerSign> serverSigns) {
        this.serverSigns = serverSigns;
    }

    public void checkSigns(){
        for (ServerSign serverSign : getServerSigns()) {
            setNoGameSignText(serverSign.getGame(),serverSign.getLocation());
            if(this.locationGameMap.containsKey(serverSign.getLocation())){
                Sign sign = (Sign) serverSign.getLocation().getBlock().getState();
                Game signGame = getGames().stream().filter(game -> game.getGameType() == serverSign.getGame()).filter(game -> game.getServerName().equalsIgnoreCase(sign.getLine(2))).findFirst().get();
                if (signGame.getGameState() != GameState.RESTARTING && signGame.getGameState() != GameState.ENDING && signGame.getGameState() != GameState.INGAME) {
                    setSignText(signGame,serverSign.getLocation());
                    return;
                }else {
                    Optional<Game> newGame = getGames().stream().filter(game -> game.getGameState() == GameState.LOBBY).filter(game -> game.getGameType() == serverSign.getGame()).filter(game -> isActive(game)).findFirst();
                    if (newGame.isPresent()) {
                        if(!locationGameMap.containsValue(newGame.get())){
                            setSignText(newGame.get(), serverSign.getLocation());
                            return;
                        }
                    }else{
                        locationGameMap.remove(serverSign.getLocation());
                        setNoGameSignText(serverSign.getGame(),serverSign.getLocation());
                        return;
                    }
                }

            }else {
                Optional<Game> newGame = getGames().stream()
                        .filter(game -> game.getGameState() == GameState.LOBBY)
                        .filter(game -> game.getGameType() == serverSign.getGame())
                        .filter(game -> isActive(game)).findFirst();
                if (newGame.isPresent()&& !locationGameMap.containsValue(newGame.get())) {
                    setSignText(newGame.get(), serverSign.getLocation());
                    locationGameMap.put(serverSign.getLocation(),newGame.get());
                }else{
                    setNoGameSignText(serverSign.getGame(),serverSign.getLocation());
                }
            }
        }
    }

    public boolean isActive(Game game){
        AtomicBoolean isActive = new AtomicBoolean(false);
        for (Game game1 : games) {
            if(game1.getServerName().equalsIgnoreCase(game.getServerName())){
                isActive.set(true);
                break;
            }else{
                isActive.set(false);
            }
        }


        System.out.println(game.getServerName() + " " + isActive.get());
        return isActive.get();
    }

    public void setSignText(Game game, Location location){
        Bukkit.getScheduler().runTask(MinigamesLobby.getInstance(),() -> {
            Sign sign = (Sign) location.getBlock().getState();

            sign.setLine(0, ChatColor.translateAlternateColorCodes('&', game.getGameType().getDisplayName()));
            sign.setLine(1, ChatColor.translateAlternateColorCodes('&', game.getGameState().getDisplay()));
            sign.setLine(2, game.getServerName());
            sign.setLine(3, game.getIngamePlayers() + " / " + game.getMaxPlayers());
            sign.update(true);
        });

    }


    public void setNoGameSignText(GameType gameType ,Location location){
        Bukkit.getScheduler().runTask(MinigamesLobby.getInstance(),() -> {
            if(location.getBlock() != null) {
                if (location.getBlock().getState() != null) {
                    Sign sign = (Sign) location.getBlock().getState();
                    sign.setLine(0, ChatColor.translateAlternateColorCodes('&', gameType.getDisplayName()));
                    sign.setLine(1, ChatColor.RED + "Geen game beschikbaar");
                    sign.setLine(2, "null");
                    sign.setLine(3, 0 + " / " + 0);
                    sign.update(true);
                }
            }
        });

    }
}
