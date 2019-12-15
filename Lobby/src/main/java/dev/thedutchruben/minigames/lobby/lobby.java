package dev.thedutchruben.minigames.lobby;

import dev.thedutchruben.minigamescore.framework.addon.Addon;
import dev.thedutchruben.minigamescore.framework.commands.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class lobby extends Addon {

    public lobby() {
        super(
                "Lobby", 1.00);
    }

    @Override
    public void onEnable() {
        System.out.println("Lobby starting");
    }

    @Override
    public void onDisable() {

    }

    @Override
    public Set<Command> getCommands() {
        return new HashSet<>(){{

        }};
    }

    @Override
    public Set<Listener> getListeners() {
        return null;
    }
}
