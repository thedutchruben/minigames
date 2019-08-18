package dev.thedutchruben.minigames.minigamescore.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtil {

    private static String prefix = "&f[&d&lMiniGames&f]&7" ;
    private final static int CENTER_PX = 154;
    private final static int MAX_PX = 250;

    public static void sendMessage(Player player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void broadCast(String message){
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void sendMessagePrefix(Player player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',getPrefix() + message));
    }

    public static void broadCastPrefix(String message){
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',getPrefix() + message));
    }
    public static String getPrefix() {
        return prefix;
    }


    public static String sendCenteredMessage(String message){
        if(message == null || message.equals(""))
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
                continue;
            }else if(previousCode == true){
                previousCode = false;
                if(c == 'l' || c == 'L'){
                    isBold = true;
                    continue;
                }else isBold = false;
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        return sb.toString() + message;
    }
}
