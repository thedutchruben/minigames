package dev.thedutchruben.minigames.minigameslobby.modules.player.listeners;

import dev.thedutchruben.core.framework.player.event.MinigamePlayerJoinEvent;
import dev.thedutchruben.core.framework.scoreboard.BPlayerBoard;
import dev.thedutchruben.minigames.minigameslobby.MinigamesLobby;
import dev.thedutchruben.minigames.minigameslobby.framework.player.LobbyPlayer;
import dev.thedutchruben.minigames.minigameslobby.modules.hologram.HologramModule;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class MinigamesPlayerJoinListener implements Listener {


    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(MinigamePlayerJoinEvent event){
        Player player = Bukkit.getPlayer(event.getMinigamesPlayer().getUuid());
        player.setLevel(event.getMinigamesPlayer().getCommonData().getLevel());
        event.getMinigamesPlayer().getBukkitPlayer().setPlayerListHeaderFooter(
                "-------------Minigames-------------\n" +
                        " \n" +
                        "Line",
                "Line\n" +
                        "\n" +
                        "-------------Minigames-------------");
        MinigamesLobby.getInstance().getPlayerModule().giveLobbyItems(event.getMinigamesPlayer().getBukkitPlayer());
        new HologramModule().setUpHolos(event.getMinigamesPlayer().getBukkitPlayer());
        MinigamesLobby.getInstance().getPlayerModule().getLobbyPlayerMap().put(event.getMinigamesPlayer().getUuid(),new LobbyPlayer(new HashMap<>(),event.getMinigamesPlayer().getUuid(), Material.AIR));
        BPlayerBoard bPlayerBoard = new BPlayerBoard("MiniGamesLobby");
        bPlayerBoard.setPlayer(event.getMinigamesPlayer().getBukkitPlayer());
        bPlayerBoard.set("?",1);
        bPlayerBoard.set("play.thedutchruben.dev",0);
    }
}
