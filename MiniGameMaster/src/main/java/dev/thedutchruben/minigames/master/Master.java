package dev.thedutchruben.minigames.master;

import dev.thedutchruben.core.server.Server;
import dev.thedutchruben.core.server.ServerInfo;
import dev.thedutchruben.core.server.ServerType;
import dev.thedutchruben.core.utils.HardWare;
import dev.thedutchruben.minigames.master.manager.server.ServerManager;
import dev.thedutchruben.minigames.master.modules.docker.DockerModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Master {
    private static Master instance;
    private List<Server> servers;

    public static void main(String[] strings) {
        new Master();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            String command;

            while ((command = input.readLine()) != null) {
                if (command.equalsIgnoreCase("help")) {
                    System.out.println("----- Help Page -----");
                    System.out.println("help - this page");
                    System.out.println("servers - see servers");
                    System.out.println("exit - shutdown the application");
                    System.out.println("---------------------");
                }
                if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("stop") || command.equalsIgnoreCase("end")) {
                    System.exit(0);
                }
                if(command.equalsIgnoreCase("servers")){
                    for (Server server : getInstance().servers) {
                        System.out.println(server.toString());
                    }
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public Master() {
        instance = this;
        servers = new ArrayList<>();
        new DockerModule();
        ServerManager.register(new Server(1,"test-game",ServerType.GAME,0,0,new ServerInfo(2.0, HardWare.getFreeMB())));
        ServerManager.register(new Server(2,"test-2",ServerType.GAME,0,2,new ServerInfo(10, HardWare.getFreeMB())));

    }

    public static Master getInstance() {
        return instance;
    }

    public List<Server> getServers() {
        return servers;
    }
}
