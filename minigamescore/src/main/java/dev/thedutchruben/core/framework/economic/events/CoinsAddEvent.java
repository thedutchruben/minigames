package dev.thedutchruben.core.framework.economic.events;

import dev.thedutchruben.core.framework.economic.EcoType;
import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import lombok.NonNull;

public class CoinsAddEvent extends EcoEvent {
    public CoinsAddEvent(@NonNull double ammount, MinigamesPlayer minigamesPlayer) {
        super(ammount, EcoType.COINS,minigamesPlayer);
    }
}
