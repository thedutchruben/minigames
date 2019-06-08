package dev.thedutchruben.core.framework.economic.events;

import dev.thedutchruben.core.framework.economic.EcoType;
import lombok.NonNull;

public class CoinsAddEvent extends EcoEvent {
    public CoinsAddEvent(@NonNull double ammount) {
        super(ammount, EcoType.COINS);
    }
}
