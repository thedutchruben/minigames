package dev.thedutchruben.core.server;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Server {
    private int id;
    private String serverName;
    private ServerType serverType;
    private int online;
    private int maxPlayers;
    private ServerState serverState;
    private ServerInfo serverInfo;

    @Override
    public String toString() {
        return "Server{" +
                "id=" + id +
                ", serverName='" + serverName + '\'' +
                ", serverType=" + serverType +
                ", online=" + online +
                ", maxPlayers=" + maxPlayers +
                ", ServerState= " + serverState.toString() +
                "\n" + serverInfo.toString() +
                '}';
    }

}
