package dev.thedutchruben.minigames.crates;

import dev.thedutchruben.minigamescore.framework.addon.Addon;
import dev.thedutchruben.minigamescore.framework.commands.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public final class Crates extends Addon {


    public Crates() {
        super("Crates", 1.0, Type.ADDON);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public Set<Command> getCommands() {
        return null;
    }

    @Override
    public Set<Listener> getListeners() {
        return null;
    }
}
