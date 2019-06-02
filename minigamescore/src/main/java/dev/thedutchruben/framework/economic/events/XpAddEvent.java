package dev.thedutchruben.framework.economic.events;

import dev.thedutchruben.framework.economic.EcoType;
import lombok.NonNull;

public class XpAddEvent extends EcoEvent{
    public XpAddEvent(@NonNull double ammount) {
        super(ammount, EcoType.XP);
    }
}
