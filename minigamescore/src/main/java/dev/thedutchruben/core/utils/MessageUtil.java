package dev.thedutchruben.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtil {

    private static String prefix = "&f[&d&lMiniGames&f]&7" ;

    public static void sendMessage(Player player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',getPrefix() + message));
    }

    public static void broadCast(String message){
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',getPrefix() + message));
    }
    public static String getPrefix() {
        return prefix;
    }
}
