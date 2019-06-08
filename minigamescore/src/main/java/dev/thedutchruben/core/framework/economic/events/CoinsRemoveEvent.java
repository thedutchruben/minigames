package dev.thedutchruben.core.framework.economic.events;

import dev.thedutchruben.core.framework.economic.EcoType;
import lombok.NonNull;

public class CoinsRemoveEvent extends EcoEvent {
    public CoinsRemoveEvent(@NonNull double ammount) {
        super(ammount, EcoType.COINS);
    }
}
