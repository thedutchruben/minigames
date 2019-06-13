package dev.thedutchruben.core.framework.economic.events;

import dev.thedutchruben.core.framework.economic.EcoType;
import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import lombok.NonNull;

public class XpRemoveEvent extends EcoEvent{
    public XpRemoveEvent(@NonNull double ammount,MinigamesPlayer minigamesPlayer) {
        super(ammount, EcoType.XP,minigamesPlayer);
    }
}
