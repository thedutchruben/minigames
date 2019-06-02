package dev.thedutchruben.framework.server;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class GameLog {
    private UUID gameId;
    private String servername;
    private Map<Date,String> data;
}
