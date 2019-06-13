package dev.thedutchruben.thesearch.modules.game.listeners;

import dev.thedutchruben.core.framework.server.events.GameEndEvent;
import dev.thedutchruben.core.utils.MessageUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameEndListener implements Listener {

    @EventHandler
    public void gameEnd(GameEndEvent event){
        MessageUtil.broadCast("-----------------------------------------------");
        MessageUtil.broadCast(MessageUtil.sendCenteredMessage("Het spel is afgelopen!"));
        MessageUtil.broadCast(" ");
        MessageUtil.broadCast(MessageUtil.sendCenteredMessage("De winaar is <winner>!").replace("<winner>",event.getMinigamesPlayer().getBukkitPlayer().getName()));
        MessageUtil.broadCast("-----------------------------------------------");

    }
}
