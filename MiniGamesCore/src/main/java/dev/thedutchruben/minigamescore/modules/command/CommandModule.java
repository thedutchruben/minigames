package dev.thedutchruben.minigamescore.modules.command;

import dev.thedutchruben.minigamescore.framework.command.Command;
import dev.thedutchruben.minigamescore.framework.command.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandModule {
    private List<Command> commands;

    public void CommandModule(){
        commands = new ArrayList<>();
        Command command = new Command("command","command.test");

        command.addSubCommand(new SubCommand("test","test") {
            @Override
            public void execute(CommandSender commandSender) {

            }
        });
        commands.add(command);
    }
}
