package dev.thedutchruben.minigamescore.framework.addon;

import dev.thedutchruben.minigamescore.framework.commands.Command;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Listener;

import java.util.Set;

public abstract class Addon {
    @Getter @Setter private State state = State.DISABLED;
    @Getter private String name;
    @Getter private double version;
    @Getter private Type type;
    public Addon(String name, double version,Type type) {
        this.name = name;
        this.version = version;
        this.type = type;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract Set<Command> getCommands();

    public abstract Set<Listener> getListeners();

    public enum State{
        ENABLED,
        DISABLED
    }

    public enum Type{
        GAMEMODE,
        ADDON
    }
}
