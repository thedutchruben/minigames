package dev.thedutchruben.autoregisterbungee;

import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.net.InetSocketAddress;
import java.util.Collection;

public final class AutoregisterBungee extends Plugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        subscribe();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerServer(String hostname){
        getProxy().getServers().put(hostname, ProxyServer.getInstance().constructServerInfo(hostname,new InetSocketAddress(hostname,25565),"",false));
    }

    public void subscribe(){
        Jedis subscriber = new Jedis("minigames-redis-1");
        subscriber.connect();

        new Thread("Redis Subscriber"){
            @Override
            public void run(){
                String[] channels = {"register", "unregister"};
                subscriber.subscribe(new JedisPubSub(){
                    @Override
                    public void onMessage(String channel, String message) {
                        switch (channel){
                            case "register":
                                registerServer(message);
                                return;
                            case "unregister":
                                getProxy().getServers().remove(message);
                        }
                    }
                }, channels);
            }
        };
    }
}
