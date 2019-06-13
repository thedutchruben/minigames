package dev.thedutchruben.core.framework.economic.events;

import dev.thedutchruben.core.framework.economic.EcoType;
import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Eco event is the main class for all the Economic events
 */
@Getter
public class EcoEvent extends Event {
    private static final HandlerList handler = new HandlerList();

    @NonNull @Setter private double ammount;
    @NonNull private final EcoType ecoType;
    @NonNull @Getter private final MinigamesPlayer minigamesPlayer;
    public EcoEvent(@NonNull double ammount,@NonNull EcoType ecoType,@NonNull MinigamesPlayer minigamesPlayer){
        this.ammount = ammount;
        this.ecoType = ecoType;
        this.minigamesPlayer = minigamesPlayer;
    }

    public static HandlerList getHandlerList() {
        return handler;
    }


    @Override
    public HandlerList getHandlers() {
        return handler;
    }
}
