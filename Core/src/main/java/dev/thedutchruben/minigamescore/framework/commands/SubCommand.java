package dev.thedutchruben.minigamescore.framework.commands;

import dev.thedutchruben.minigamescore.utils.Colors;
import lombok.Data;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class SubCommand {
    private Command command;
    private String subcommand;
    private String permission;
    private List<String> alias;
    private String description = Colors.WARNING.getChatColor() + "No description";
    private List<SubCommand> subCommands = new ArrayList<>();
    public SubCommand(Command command, String subcommand) {
        this.command = command;
        this.subcommand = subcommand;
        this.permission = command.getPermission() + "." +subcommand;
        alias = new ArrayList<>();
        alias.add(subcommand);
    }

    public void addAlias(String subcommand) {
        alias.add(subcommand);
    }

    public abstract void execute(CommandSender commandSender,String[] args);

}
