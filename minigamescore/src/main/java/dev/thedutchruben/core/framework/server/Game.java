package dev.thedutchruben.core.framework.server;


import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import dev.thedutchruben.core.minigamescore;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Game class is the class were every setting of the game gets registerd and were you can find the basic methots
 * @author TheDutchRuben
 */

public class Game {

    private transient static Game game;
    private transient final MongoCollection mongoCollection;
    private transient final Gson gson = minigamescore.getInstance().getMongoDb().getGson();

    private String serverName;
    private GameState gameState;
    private GameType gameType;
    private int minPlayers;
    private int maxPlayers;
    private int ingamePlayers = 0;
    private List<Location> spawnLocations;
    private Location lobby;
    private boolean randomSpawn = false;
    private transient BukkitTask startRunnable;
    private boolean continuGame = false;
    /**
     * Register the game for the database / lobby
     * @param serverName The bungee server name
     * @param gameState The default gameState of the server
     * @param gameType The gameType from the server
     * @param minPlayers The minimum amount of players that the game needs
     * @param maxPlayers The maximum amount of players that the game can have
     */
    public Game(String serverName,GameState gameState ,GameType gameType, int minPlayers, int maxPlayers){
        mongoCollection = minigamescore.getInstance().getMongoDb().getMongoDatabase().getCollection("servers");

        this.serverName = serverName;
        this.gameState = gameState;
        this.gameType = gameType;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.spawnLocations = new ArrayList<>();
        updateGame();
        game = this;
    }

    /**
     *
     * @return The gameState of the game
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     *
     * @return Returns the gameType of the game
     */
    public GameType getGameType() {
        return gameType;
    }

    /**
     *
     * @return The minimum amount of players that the game needs to start
     */
    public int getMinPlayers() {
        return minPlayers;
    }

    /**
     * if the random spawn = true than the plugin loops though the spawn locations and give every player a uniq spawn location
     * if the random spawn = false than the plugin loopts though the spawn locations and give every player a spawn location and it dosnt matter if there spawns alreddy a player
     * @return boolean for the random spawn
     */
    public boolean isRandomSpawn() {
        return randomSpawn;
    }

    /**
     *
     * @param randomSpawn boolean if it is a random spawn or a non random spawn
     */
    public void setRandomSpawn(boolean randomSpawn) {
        this.randomSpawn = randomSpawn;
    }

    /**
     *
     * @return The maximum amount of players that the game can have
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Set's the state of the game
     * @param gameState GameState
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        save();
    }

    /**
     * Set's the minimum amount of players that the game needs
     * @param minPlayers Min Players
     */
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    /**
     * Set's the max amount of players that the game can have
     * @param maxPlayers Max Players
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     *
     * @return List with spawn locations
     */
    public List<Location> getSpawnLocations() {
        return spawnLocations;
    }

    /**
     *
     * @return Location object for the lobby of the game
     */
    public Location getLobby() {
        return lobby;
    }

    /**
     * Set's the Lobby location for the game
     * @param lobby Location object for the spawn
     */
    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }

    /**
     * Add's a location to the spawn locations
     * @param location Location to add in the spawnLocations list;
     */
    public void addSpawnLocation(Location location){
        spawnLocations.add(location);
    }

    /**
     *
     * @return the game
     */
    public static Game getGame() {
        return game;
    }

    public BukkitTask getStartRunnable() {
        return startRunnable;
    }

    public void setStartRunnable(BukkitTask startRunnable) {
        this.startRunnable = startRunnable;
    }

    public boolean isContinuGame() {
        return continuGame;
    }

    public void setContinuGame(boolean continuGame) {
        this.continuGame = continuGame;
    }

    public String getServerName() {
        return serverName;
    }


    private static void updateGame(){
        Bukkit.getScheduler().runTaskTimerAsynchronously(minigamescore.getInstance(),() -> Game.getGame().save(),0,20);

    }

    public int getIngamePlayers() {
        return ingamePlayers;
    }

    public CompletableFuture<Void> save() {
        return CompletableFuture.runAsync(() -> {
            ingamePlayers = Bukkit.getOnlinePlayers().size();
            Document document = Document.parse(gson.toJson(this, Game.class));
            mongoCollection.replaceOne(new BasicDBObject().append("_id", getServerName()), document, new UpdateOptions().upsert(true));
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }
}
