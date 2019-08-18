package dev.thedutchruben.minigames.master.manager.server;

import dev.thedutchruben.core.server.Server;
import dev.thedutchruben.minigames.master.Master;

public class ServerManager {

    public static void register(Server server){
        Master.getInstance().getServerTypeServerMap().put(server.getServerType(),server);
    }

    public static void unRegister(Server server){
        Master.getInstance().getServerTypeServerMap().remove(server);
    }
}
