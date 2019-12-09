package dev.thedutchruben.autoregisterbungee;

import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.net.InetSocketAddress;
import java.util.Collection;

public final class AutoregisterBungee extends Plugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerServer(String name,String hostname){
        getProxy().getServers().put(name, ProxyServer.getInstance().constructServerInfo(name,new InetSocketAddress(hostname,25565),"",false));
    }
}
