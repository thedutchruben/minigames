package dev.thedutchruben.minigames.minigameslobby.modules.player.listeners;

import dev.thedutchruben.core.framework.player.event.MinigamePlayerJoinEvent;
import dev.thedutchruben.minigames.minigameslobby.MinigamesLobby;
import dev.thedutchruben.core.utils.Utils;
import dev.thedutchruben.minigames.minigameslobby.modules.hologram.HologramModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class MinigamesPlayerJoinListener implements Listener {


    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(MinigamePlayerJoinEvent event){
        Player player = Bukkit.getPlayer(event.getMinigamesPlayer().getUuid());
        player.setLevel(event.getMinigamesPlayer().getCommonData().getLevel());
        MinigamesLobby.getInstance().getPlayerModule().giveLobbyItems(event.getMinigamesPlayer().getBukkitPlayer());
        new HologramModule().setUpBasicStatsHologram(event.getMinigamesPlayer().getBukkitPlayer());
       // MinigamesLobby.getInstance().getScoreboards().stream().filter(locationBoard -> locationBoard.getMaterial() == Material.AIR).findFirst().get().getScoreboard().create(player);

        Utils.circle(event.getMinigamesPlayer().getBukkitPlayer().getLocation(),
                3,4,true ,true,2).forEach(location ->{

        });
    }
}
