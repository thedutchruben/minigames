package dev.thedutchruben.minigames.minigamescore.utils;

import dev.thedutchruben.minigames.minigamescore.MiniGamesCore;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class BungeeUtil {
    /**
     * Register the bungeecord channel for sending the player
     * @param plugin The plugin that registers the statement
     */
    public static void registerBungeeCordOut(Plugin plugin) {
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }

    /**
     * Send a player to another server
     * @param player The player to send
     * @param targetServer The servername where the player have to go
     */
    public static void send(Player player, String targetServer) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(targetServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(MiniGamesCore.getInstance(), "BungeeCord", b.toByteArray());
    }

}
