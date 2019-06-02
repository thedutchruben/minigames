package dev.thedutchruben.framework.player;


import dev.thedutchruben.framework.scoreboard.Scoreboard;
import dev.thedutchruben.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MinigamesPlayer {
    private UUID uuid;
    private ChatColor nameColor;
    private MiniGamesRank miniGamesRank;
    private int level = 1;
    private int xp = 0 ;
    private int coins = 0;
    private transient Scoreboard scoreboard;

    public MinigamesPlayer(UUID uuid){
        this.uuid = uuid;
        this.nameColor = ChatColor.LIGHT_PURPLE;
        this.miniGamesRank = MiniGamesRank.NOOB;
    }


    public void updateScoreboard(){
        scoreboard.apply(
                Bukkit.
                        getPlayer
                                (uuid));
    }


    public UUID getUuid() {
        return uuid;
    }


    public ChatColor getNameColor() {
        return nameColor;
    }

    public void setNameColor(ChatColor nameColor) {
        this.nameColor = nameColor;
    }

    public MiniGamesRank getMiniGamesRank() {
        return miniGamesRank;
    }

    public void setMiniGamesRank(MiniGamesRank miniGamesRank) {
        this.miniGamesRank = miniGamesRank;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
        scoreboard.apply(getBukkitPLayer());
    }


    public Player getBukkitPLayer(){
        return Bukkit.getPlayer(uuid);
    }

    public void addCoins(int coins){
        setCoins(
                getCoins()
                        +
                        coins);
        MessageUtil.sendMessage(getBukkitPLayer(),"Je hebt &8" + coins + "&7 coins ondvangen!");
    }

    public void removeCoins(int coins){
        setCoins(getCoins() - coins);
    }

    public void addXp(int xp){
        setXp(getXp() + xp);
        MessageUtil.sendMessage(getBukkitPLayer(),"Je hebt &8" + xp + "&7 xp ondvangen!");

    }
}
