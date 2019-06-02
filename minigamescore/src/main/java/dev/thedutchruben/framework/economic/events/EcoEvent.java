package dev.thedutchruben.framework.economic.events;

import dev.thedutchruben.framework.economic.EcoType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class EcoEvent extends Event {
    private static final HandlerList handler = new HandlerList();

    @NonNull @Setter private double ammount;
    @NonNull private final EcoType ecoType;

    public EcoEvent(@NonNull double ammount,@NonNull EcoType ecoType){
        this.ammount = ammount;
        this.ecoType = ecoType;
    }

    public static HandlerList getHandlerList() {
        return handler;
    }

    @Override
    public HandlerList getHandlers() {
        return handler;
    }
}
