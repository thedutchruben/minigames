package dev.thedutchruben.core.framework.economic.events;

import dev.thedutchruben.core.framework.economic.EcoType;
import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import lombok.NonNull;

public class CoinsRemoveEvent extends EcoEvent {
    public CoinsRemoveEvent(@NonNull double ammount, MinigamesPlayer minigamesPlayer) {
        super(ammount, EcoType.COINS,minigamesPlayer);
    }
}
