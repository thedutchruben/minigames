package dev.thedutchruben.minigames.master;

import dev.thedutchruben.core.server.Server;
import dev.thedutchruben.minigames.master.modules.docker.DockerModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Master {
    private static Master instance;
    private List<Server> servers;

    public Master() {
        instance = this;
        servers = new ArrayList<>();
        new DockerModule();
    }

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
                if (command.equalsIgnoreCase("servers")) {
                    for (Server server : getInstance().servers) {
                        System.out.println(server.toString());
                    }
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public static Master getInstance() {
        return instance;
    }

    public List<Server> getServers() {
        return servers;
    }
}
