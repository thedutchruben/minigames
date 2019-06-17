package dev.thedutchruben.minigames.minigameslobby.framework.serversign;

import dev.thedutchruben.core.framework.server.GameType;
import dev.thedutchruben.minigames.minigameslobby.MinigamesLobby;
import org.bukkit.Location;


public class ServerSign {
    private Location location;
    private GameType game;

    public ServerSign(Location location, GameType game) {
        MinigamesLobby.getInstance().getServersignModule().getServerSigns().add(this);
        MinigamesLobby.getInstance().getServersignModule().saveGameSign(this);
        this.location = location;
        this.game = game;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public GameType getGame() {
        return game;
    }

    public void setGame(GameType game) {
        this.game = game;
    }


}
