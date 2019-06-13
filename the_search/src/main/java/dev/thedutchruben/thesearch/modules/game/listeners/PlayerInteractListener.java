package dev.thedutchruben.thesearch.modules.game.listeners;

import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.core.framework.server.events.GameEndEvent;
import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.utils.Holograms;
import dev.thedutchruben.thesearch.Thesearch;
import dev.thedutchruben.thesearch.framework.player.SearchPlayer;
import dev.thedutchruben.core.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent interactEvent){
        if(interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(interactEvent.getClickedBlock().getType() == Material.PLAYER_HEAD){
                interactEvent.setCancelled(true);
                interactEvent.setUseInteractedBlock(Event.Result.DENY);
                SearchPlayer searchPlayer = Thesearch.getInstance().getPlayerModule().getSearchPlayers().get(interactEvent.getPlayer().getUniqueId());
                if(!searchPlayer.getLocations().contains(interactEvent.getClickedBlock().getLocation())){
                    searchPlayer.getLocations().add(interactEvent.getClickedBlock().getLocation());
                    dev.thedutchruben.core.utils.Holograms holograms = new Holograms(Arrays.asList(ChatColor.GREEN + "✔"),interactEvent.getClickedBlock().getLocation());

                    holograms.showPlayer(interactEvent.getPlayer());
                    MiniGamesCore.getInstance().getPlayerModule().getMinigamesPlayers().values().forEach(minigamesPlayer -> Thesearch.getInstance().getGameMode().setScoreboard(minigamesPlayer));
                    MessageUtil.sendMessage(interactEvent.getPlayer(),ChatColor.GREEN + "Je hebt een head gevonden! zoek snel nog meer! (" + searchPlayer.getLocations().size() + " / " + Thesearch.getInstance().getMap().getAmmount()+ ")");
                        //todo add 1 coins
                    if(searchPlayer.getLocations().size() == Thesearch.getInstance().getMap().getAmmount()){
                        Bukkit.broadcastMessage(ChatColor.GOLD + interactEvent.getPlayer().getName() + " heeft gewonnen!");
                        Bukkit.getServer().getPluginManager().callEvent(new GameEndEvent(Game.getGame()));
                        interactEvent.getPlayer().setPlayerListName("[%amount%]".replace("%amount%",searchPlayer.getLocations().size() + "") + interactEvent.getPlayer().getName());
                        //todo add 10 coins
                    }
                }else{
                    MessageUtil.sendMessage(interactEvent.getPlayer(),ChatColor.RED + "Je hebt deze head al gevonden zoek snel een andere!");

                }
                interactEvent.setCancelled(true);
            }
        }
    }
}
