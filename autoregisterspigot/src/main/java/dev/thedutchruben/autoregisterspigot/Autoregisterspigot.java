package dev.thedutchruben.autoregisterspigot;

import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class Autoregisterspigot extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            publish("register", InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            publish("unregister", InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void publish(String channel,  String message){
        try(Jedis publisher = new Jedis("minigames-redis-1")){
            publisher.publish(channel, message);
        }
    }


}
