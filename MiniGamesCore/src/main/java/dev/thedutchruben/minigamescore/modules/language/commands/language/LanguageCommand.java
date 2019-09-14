package dev.thedutchruben.minigamescore.modules.language.commands.language;

import dev.thedutchruben.minigamescore.framework.command.Command;
import dev.thedutchruben.minigamescore.modules.language.commands.language.sub.CreateSubCommand;
import org.bukkit.command.CommandSender;

public class LanguageCommand extends Command {
    public LanguageCommand(String command, String permission) {
        super(command, permission);
        addSubCommand(new CreateSubCommand(this, "create", permission + ".create"));
    }

    @Override
    public void executeDefault(CommandSender commandSender, String[] args) {

    }
}
