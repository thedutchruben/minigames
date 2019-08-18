package dev.thedutchruben.minigames.master.manager.server;

import dev.thedutchruben.core.server.Server;
import dev.thedutchruben.minigames.master.Master;

import java.util.Optional;

public class ServerManager {

    public static void register(Server server){
        Master.getInstance().getServers().add(server);
        System.out.println("Register | " + server.toString());
    }

    public static void unRegister(Server server){
        Master.getInstance().getServers().remove(server);
        System.out.println("Un Register | " +server.toString());
    }

    public static void unRegister(int id){
        Optional<Server> servers = Master.getInstance().getServers().stream().filter(server -> server.getId() == id).findAny();
        servers.ifPresent(ServerManager::unRegister);
    }
}
