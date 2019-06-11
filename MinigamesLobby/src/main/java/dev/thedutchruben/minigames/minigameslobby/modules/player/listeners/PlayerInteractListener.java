package dev.thedutchruben.minigames.minigameslobby.modules.player.listeners;

import dev.thedutchruben.minigames.minigameslobby.MinigamesLobby;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        if(event.getItem() != null){
            if(event.getAction() == Action.RIGHT_CLICK_AIR){
                if(event.getItem().getType() == Material.PLAYER_HEAD) {
                    MinigamesLobby.getInstance().getPlayerModule().playerInfo(event.getPlayer()).open(event.getPlayer());
                }
            }
        }
    }
}
