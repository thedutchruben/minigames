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
        Bukkit.getScheduler().runTaskLater(MinigamesLobby.getInstance(),() -> {
            if(!serverSigns.isEmpty()) {
                locationGameMap.clear();
                serverSigns.forEach(serverSign -> {
                    setNoGameSignText(serverSign.getGame(), serverSign.getLocation());
                });
            }
        },20);

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
        System.out.println(1);
        if(!locationGameMap.isEmpty()){
            System.out.println(2);
            locationGameMap.forEach((location, game) -> {
                System.out.println(3);
                if(game.getGameState() != GameState.LOBBY){
                    System.out.println(4);
                    setNoGameSignText(game.getGameType(),location);
                    locationGameMap.remove(location);
                }else{
                    System.out.println(5);
                    setSignText(game,location);
                }
            });
        }
        System.out.println(10);
        for(GameType gameType : GameType.values()) {
            System.out.println(gameType.getDisplayName());
            getServerSigns().stream().filter(serverSign -> serverSign.getGame() == gameType).forEach(serverSign -> {
                System.out.println(serverSign.getLocation().toString());
                Iterator<Game> gameIterator = getGames().stream().filter(game -> game.getGameType()  == gameType).iterator();
                System.out.println(11);
                if(!gameIterator.hasNext()){
                    System.out.println(12);
                    setNoGameSignText(gameType,serverSign.getLocation());
                }else {
                    System.out.println(13);
                    Optional<Game> game2 = getGames().stream().filter(game -> game.getGameType() == gameType).filter(game -> !locationGameMap.containsKey(serverSign.getLocation())).filter(game -> game.getGameState() == GameState.LOBBY).findFirst();
                    if (game2.isPresent()) {
                        System.out.println(14);
                        setSignText(game2.get(), serverSign.getLocation());
                        locationGameMap.put(serverSign.getLocation(), game2.get());
                    }
                }
            });
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
