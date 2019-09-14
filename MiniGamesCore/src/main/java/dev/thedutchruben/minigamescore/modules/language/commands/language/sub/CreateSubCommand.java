package dev.thedutchruben.minigamescore.modules.language.commands.language.sub;

import dev.thedutchruben.minigamescore.framework.command.Command;
import dev.thedutchruben.minigamescore.framework.command.SubCommand;
import org.bukkit.command.CommandSender;

public class CreateSubCommand extends SubCommand {


    public CreateSubCommand(Command command, String subcommand, String permission) {
        super(command, subcommand, permission);
    }

    @Override
    public void execute(CommandSender commandSender) {
            commandSender.sendMessage("created");
    }
}
