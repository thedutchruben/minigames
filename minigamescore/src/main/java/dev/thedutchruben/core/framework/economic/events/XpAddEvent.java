package dev.thedutchruben.core.framework.economic.events;

import dev.thedutchruben.core.framework.economic.EcoType;
import lombok.NonNull;

public class XpAddEvent extends EcoEvent{
    public XpAddEvent(@NonNull double ammount) {
        super(ammount, EcoType.XP);
    }
}
