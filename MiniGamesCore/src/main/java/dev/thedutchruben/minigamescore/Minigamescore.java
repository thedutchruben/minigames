package dev.thedutchruben.minigamescore;

import dev.thedutchruben.core.gamemode.GameMode;
import dev.thedutchruben.core.server.Server;
import dev.thedutchruben.core.server.ServerInfo;
import dev.thedutchruben.core.server.ServerState;
import dev.thedutchruben.core.server.ServerType;
import dev.thedutchruben.core.utils.HardWare;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public final class Minigamescore extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void serverSetup() throws UnknownHostException {
        new Server(InetAddress.getLocalHost().getHostName(), ServerType.GAME,0,10, ServerState.LOBBY,new ServerInfo(20, HardWare.getFreeMB()));

    }
}
