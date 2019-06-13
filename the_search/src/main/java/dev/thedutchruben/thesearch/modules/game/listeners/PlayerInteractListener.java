package dev.thedutchruben.thesearch.modules.game.listeners;

import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.core.framework.server.events.GameEndEvent;
import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.modules.player.PlayerModule;
import dev.thedutchruben.core.utils.Holograms;
import dev.thedutchruben.thesearch.Thesearch;
import dev.thedutchruben.thesearch.framework.player.SearchPlayer;
import dev.thedutchruben.core.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.PLAYER_HEAD){
                event.setCancelled(true);
                event.setUseInteractedBlock(Event.Result.DENY);
                SearchPlayer searchPlayer = Thesearch.getInstance().getPlayerModule().getSearchPlayers().get(event.getPlayer().getUniqueId());
                if(!searchPlayer.getLocations().contains(event.getClickedBlock().getLocation())){
                    searchPlayer.getLocations().add(event.getClickedBlock().getLocation());
                    dev.thedutchruben.core.utils.Holograms holograms = new Holograms(Arrays.asList(ChatColor.GREEN + "✔"),event.getClickedBlock().getLocation());
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP,20,2);
                    holograms.showPlayer(event.getPlayer());
                    MiniGamesCore.getInstance().getPlayerModule().getMinigamesPlayers().values().forEach(minigamesPlayer -> Thesearch.getInstance().getGameMode().setScoreboard(minigamesPlayer));
                    MessageUtil.sendMessage(event.getPlayer(),ChatColor.GREEN + "Je hebt een head gevonden! zoek snel nog meer! (" + searchPlayer.getLocations().size() + " / " + Thesearch.getInstance().getMap().getAmmount()+ ")");
                        //todo add 1 coins
                    if(searchPlayer.getLocations().size() == Thesearch.getInstance().getMap().getAmmount()){
                        Bukkit.broadcastMessage(ChatColor.GOLD + event.getPlayer().getName() + " heeft gewonnen!");
                        Bukkit.getServer().getPluginManager().callEvent(new GameEndEvent(Game.getGame(), PlayerModule.getMinigamesPlayer(event.getPlayer())));
                        event.getPlayer().setPlayerListName("[%amount%]".replace("%amount%",searchPlayer.getLocations().size() + "") + event.getPlayer().getName());
                        //todo add 10 coins
                    }
                }else{
                    MessageUtil.sendMessage(event.getPlayer(),ChatColor.RED + "Je hebt deze head al gevonden zoek snel een andere!");

                }
                event.setCancelled(true);
            }
        }
    }
}
