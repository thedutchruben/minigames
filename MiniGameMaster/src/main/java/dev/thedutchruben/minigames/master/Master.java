package dev.thedutchruben.minigames.master;

import dev.thedutchruben.core.server.Server;
import dev.thedutchruben.core.server.ServerType;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Master {
    private static Master instance;
    private Map<ServerType, Server> serverTypeServerMap = new ConcurrentHashMap<>();

    public static void main(String[] strings){

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            String command;

            while ((command = input.readLine()) != null) {
                if (command.equalsIgnoreCase("help")) {
                    System.out.println("----- Help Page -----");
                    System.out.println("help - this page");
                    System.out.println("exit - shutdown the application");
                    System.out.println("---------------------");
                }
                if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("stop") || command.equalsIgnoreCase("end")) {
                    System.exit(0);
                }
                if(command.equalsIgnoreCase("servers")){
                getInstance().getServerTypeServerMap().forEach((serverType, server) -> {
                    System.out.println(serverType.name());
                    System.out.println(server.toString());

                });
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public Master() {
        instance = this;
    }

    public static Master getInstance() {
        return instance;
    }


    public Map<ServerType, Server> getServerTypeServerMap() {
        return serverTypeServerMap;
    }
}
