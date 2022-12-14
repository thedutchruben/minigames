package dev.thedutchruben.minigamescore.framework.registery;

import dev.thedutchruben.minigamescore.Minigamescore;
import dev.thedutchruben.minigamescore.framework.commands.Command;

import java.util.Set;

public abstract class Module {



    abstract public void onLoad();

    abstract public void onEnable();

    abstract public void onDisable();

    abstract public Set<Command> getCommands();

}
