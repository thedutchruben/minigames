package dev.thedutchruben.thesearch.modules.game.listeners;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.economic.events.XpAddEvent;
import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.core.framework.server.GameState;
import dev.thedutchruben.core.framework.server.events.GameEndEvent;
import dev.thedutchruben.core.modules.player.PlayerModule;
import dev.thedutchruben.core.utils.MessageUtil;
import dev.thedutchruben.thesearch.Thesearch;
import dev.thedutchruben.thesearch.framework.player.SearchPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.PLAYER_HEAD) {
                if (Game.getGame().getGameState() == GameState.INGAME) {
                    event.setUseInteractedBlock(Event.Result.DENY);
                    SearchPlayer searchPlayer = Thesearch.getInstance().getPlayerModule().getSearchPlayers().get(event.getPlayer().getUniqueId());

                    if (searchPlayer.getLocations().contains(event.getClickedBlock().getLocation())) {
                        MessageUtil.sendMessagePrefix(event.getPlayer(), "Je hebt dit stukje kaas al gevonden!");
                        return;
                    } else {

                        if (!searchPlayer.getLocations().contains(event.getClickedBlock().getLocation())) {
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 2);
                            event.getPlayer().setPlayerListName("[%amount%]".replace("%amount%", searchPlayer.getLocations().size()+ "") + event.getPlayer().getName());
                            PlayerModule.getMinigamesPlayer(event.getPlayer()).getTheSearchData().setTotalHeadsFound(PlayerModule.getMinigamesPlayer(event.getPlayer()).getTheSearchData().getTotalHeadsFound() + 1);
                            MiniGamesCore.getInstance().getPlayerModule().getMinigamesPlayers().values().forEach(minigamesPlayer -> Thesearch.getInstance().getGameMode().createScoreboard(minigamesPlayer));
                            MessageUtil.sendMessage(event.getPlayer(), ChatColor.GREEN + "Je hebt een head gevonden! zoek snel nog meer! (" + searchPlayer.getLocations().size() + " / " + Thesearch.getInstance().getMap().getAmmount() + ")");
                            Bukkit.getServer().getPluginManager().callEvent(new XpAddEvent(0.1,PlayerModule.getMinigamesPlayer(event.getPlayer())));
                            //todo add 2 coins
                            searchPlayer.getLocations().add(event.getClickedBlock().getLocation());
                            if (searchPlayer.getLocations().size() == Thesearch.getInstance().getMap().getAmmount()) {
                                Bukkit.getServer().getPluginManager().callEvent(new GameEndEvent(Game.getGame(), PlayerModule.getMinigamesPlayer(event.getPlayer())));
                                Bukkit.getServer().getPluginManager().callEvent(new XpAddEvent(3.0,PlayerModule.getMinigamesPlayer(event.getPlayer())));
                                //todo add 10 coins
                            }
                        }
                    }
                }
            }
        }
    }
}
