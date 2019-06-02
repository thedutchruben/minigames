package dev.thedutchruben.utils;

import dev.thedutchruben.minigamescore;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class BungeeUtil {
    public static void registerBungeeCordOut(Plugin plugin) {
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }

    public static void send(Player player, String targetServer) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(targetServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(minigamescore.getInstance(), "BungeeCord", b.toByteArray());
    }

}
