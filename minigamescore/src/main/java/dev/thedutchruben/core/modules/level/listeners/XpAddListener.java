package dev.thedutchruben.core.modules.level.listeners;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.economic.events.XpAddEvent;
import dev.thedutchruben.core.framework.level.Level;
import dev.thedutchruben.core.framework.level.events.LevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class XpAddListener implements Listener {


    @EventHandler(priority = EventPriority.LOWEST)
    public void onXPAdd(XpAddEvent event){
        event.getMinigamesPlayer().getCommonData().addXp((int) event.getAmmount());
        Level levelCheck = MiniGamesCore.getInstance().getLevelModule().getLevel(event.getMinigamesPlayer().getCommonData().getLevel() + 1);
        if(levelCheck.getNeedXp() <= event.getMinigamesPlayer().getCommonData().getXp() || levelCheck.getNeedXp() == event.getMinigamesPlayer().getCommonData().getXp()){
            Bukkit.getPluginManager().callEvent(new LevelUpEvent(event.getMinigamesPlayer(),levelCheck.getLevel() - 1, levelCheck.getLevel()));
        }

    }
}
