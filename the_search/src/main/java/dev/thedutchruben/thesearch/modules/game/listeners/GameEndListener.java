package dev.thedutchruben.thesearch.modules.game.listeners;

import dev.thedutchruben.core.framework.server.events.GameEndEvent;
import dev.thedutchruben.core.utils.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameEndListener implements Listener {

    @EventHandler
    public void gameEnd(GameEndEvent event){
        event.getMinigamesPlayer().getTheSearchData().setGamesWon(event.getMinigamesPlayer().getTheSearchData().getGamesWon() + 1);
        MessageUtil.broadCast(ChatColor.GRAY + "-----------------------------------------------");
        MessageUtil.broadCast(MessageUtil.sendCenteredMessage(ChatColor.WHITE +"Het spel is afgelopen!"));
        MessageUtil.broadCast(" ");
        MessageUtil.broadCast(MessageUtil.sendCenteredMessage(ChatColor.WHITE + "De winaar is "+ChatColor.GOLD+"<winner>"+ChatColor.WHITE+"!").replace("<winner>",event.getMinigamesPlayer().getBukkitPlayer().getName()));
        MessageUtil.broadCast(ChatColor.GRAY + "-----------------------------------------------");

    }
}
