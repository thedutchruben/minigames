package dev.thedutchruben.thesearch.modules.game.listeners;

import dev.thedutchruben.thesearch.Thesearch;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class HeadPlaceListener implements Listener {


    @EventHandler
    public void onPlace(BlockPlaceEvent blockPlaceEvent){
        if(blockPlaceEvent.getBlockPlaced().getType() == Material.PLAYER_HEAD){
            Thesearch.getInstance().getMap().getHeadLocations().add(blockPlaceEvent.getBlockPlaced().getLocation());
            Thesearch.getInstance().save(Thesearch.getInstance().getMap());
            blockPlaceEvent.setBuild(false);
            blockPlaceEvent.setCancelled(true);
        }
    }
}
