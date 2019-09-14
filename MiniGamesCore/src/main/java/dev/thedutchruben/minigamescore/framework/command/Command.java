package dev.thedutchruben.minigamescore.framework.command;

import co.aurasphere.jyandex.dto.Language;
import dev.thedutchruben.minigamescore.modules.command.CommandModule;
import dev.thedutchruben.minigamescore.modules.language.LanguageModule;
import lombok.Setter;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class Command extends org.bukkit.command.Command {

    private List<SubCommand> subCommands;
    @Setter
    private boolean defaultList = true;

    public Command(String command, String permission) {
        super(command);
        setPermission(permission);
        this.subCommands = new ArrayList<>();
        CommandModule.getCommandMap().register("minigamescore", this);
    }

    public void addSubCommand(SubCommand subCommand) {
        subCommands.add(subCommand);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length <= 0) {
            if (defaultList) {
                for (SubCommand command : subCommands) {
                    sender.sendMessage(commandLabel + " " + command.getCommand() + LanguageModule.translate(" - " + command.getDescription(), Language.ENGLISH));
                }
            } else {
                executeDefault(sender, args);
            }
        }
        Optional<SubCommand> subCommand = subCommands.stream().filter(subCommand1 -> subCommand1.getSubcommand().equalsIgnoreCase(args[0])).findAny();
        if (subCommand.isPresent()) {
            SubCommand subCommand1 = subCommand.get();
            if (sender.hasPermission(subCommand1.getPermission())) {
                subCommand1.execute(sender);
            } else {
                sender.sendMessage(LanguageModule.translate("Je hebt geen toestemming om dit uit te voeren!", Language.ENGLISH));
            }
        } else {
            if (defaultList) {
                for (SubCommand command : subCommands) {
                    sender.sendMessage(commandLabel + " " + command.getCommand() + LanguageModule.translate(" - " + command.getDescription(), Language.ENGLISH));
                }
            } else {
                executeDefault(sender, args);
            }
        }
        return false;
    }

    public abstract void executeDefault(CommandSender commandSender, String[] args);
}
