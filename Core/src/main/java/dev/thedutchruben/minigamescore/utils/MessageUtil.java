package dev.thedutchruben.minigamescore.utils;

import org.bukkit.entity.Player;

public class MessageUtil {
    private static String PREFIX = "[MiniGames]";

    public static void sendMessage(Player player, Colors colors, String message,boolean prefix){
        if (prefix){
            player.sendMessage(PREFIX + colors.getChatColor() + message);
        }else{
            player.sendMessage(colors.getChatColor() + message);

        }
    }

    public static void sendMessage(Player player, Colors colors, String message, boolean prefix, Replacement... replacements){
        String messages = colors.getChatColor() + message;
        for (String s : message.split(" ")) {
            for (Replacement replacement : replacements) {
                if(s.equalsIgnoreCase(replacement.getKey())){
                    s.replace(s, Colors.HIGH_LIGHT.getChatColor() + replacement.getKey()+ colors.getChatColor());
                }
            }
        }
        if(prefix){
            player.sendMessage(PREFIX + messages);
        }else{
            player.sendMessage(messages);

        }

    }
}
