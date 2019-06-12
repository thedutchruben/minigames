package dev.thedutchruben.core.framework.level.events;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.level.Level;
import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LevelUpEvent  extends Event {
    private static final HandlerList handler = new HandlerList();

    @Getter
    private final MinigamesPlayer minigamesPlayer;

    @Getter
    private final int oldLevel;

    @Getter
    private final int newLevel;

    public LevelUpEvent(MinigamesPlayer minigamesPlayer,int oldLevel,int newLevel) {
        this.minigamesPlayer = minigamesPlayer;
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }

    public static HandlerList getHandlerList() {
        return handler;
    }

    @Override
    public HandlerList getHandlers() {
        return handler;
    }

    public Level getNewLevel(){
        return MiniGamesCore.getInstance().getLevels().stream().filter(level -> level.getLevel() == this.newLevel).findFirst().get();
    }

    public Level getOldLevel(){
        return MiniGamesCore.getInstance().getLevels().stream().filter(level -> level.getLevel() == this.oldLevel).findFirst().get();
    }
}
