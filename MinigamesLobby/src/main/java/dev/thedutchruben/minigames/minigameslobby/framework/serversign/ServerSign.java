package dev.thedutchruben.minigames.minigameslobby.framework.serversign;

import dev.thedutchruben.framework.server.GameState;
import dev.thedutchruben.framework.server.GameType;
import dev.thedutchruben.minigames.minigameslobby.MinigamesLobby;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


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

    public static void setSign(){
        MinigamesLobby.getInstance().getServersignModule().getGames().forEach(game1 -> {
            if(MinigamesLobby.getInstance().getServersignModule().getServerSigns() == null){
                MinigamesLobby.getInstance().getServersignModule().setServerSigns(new ArrayList<>());
            }
            if(MinigamesLobby.getInstance().getServersignModule().getServerSigns().isEmpty()){
                return;
            }
            List<ServerSign> serverSignIterator = new ArrayList<>(MinigamesLobby.getInstance().getServersignModule().getServerSigns().stream().filter(serverSign -> serverSign.getGame() == game1.getGameType()).collect(Collectors.toList()));

            Block signblock = serverSignIterator.get(new Random().nextInt(serverSignIterator.size())).getLocation().getBlock();
            Sign sign = (Sign) signblock.getState();
            if (game1.getGameState() != GameState.RESTARTING && game1.getGameState() != GameState.ENDING && game1.getGameState() != GameState.INGAME) {
                sign.setLine(0, ChatColor.translateAlternateColorCodes('&', game1.getGameType().getDisplayName()));
                sign.setLine(1, ChatColor.translateAlternateColorCodes('&', game1.getGameState().getDisplay()));
                sign.setLine(2, game1.getServerName());
                sign.setLine(3, game1.getIngamePlayers() + " / " + game1.getMaxPlayers());
                sign.update(true);
            }else{
                sign.setLine(0, "");
                sign.setLine(1, "");
                sign.setLine(2, "");
                sign.setLine(3, "");
                sign.update(true);
            }


        });
    }

}
