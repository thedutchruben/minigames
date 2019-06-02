package dev.thedutchruben.framework.economic.events;

import dev.thedutchruben.framework.economic.EcoType;
import lombok.NonNull;

public class XpRemoveEvent extends EcoEvent{
    public XpRemoveEvent(@NonNull double ammount) {
        super(ammount, EcoType.XP);
    }
}
