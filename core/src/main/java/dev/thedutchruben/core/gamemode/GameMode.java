package dev.thedutchruben.core.gamemode;

import dev.thedutchruben.core.server.Server;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Data
public class GameMode {
    private String name;
    private String icon;
    private String dockerName;
    private String displayName;
    private int minGames;
    private int maxGames;
    private List<Server> activeServers;

}
