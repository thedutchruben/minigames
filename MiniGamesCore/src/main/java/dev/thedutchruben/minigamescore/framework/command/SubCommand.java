package dev.thedutchruben.minigamescore.framework.command;

import lombok.Data;
import org.bukkit.command.CommandSender;

@Data
public abstract class SubCommand {
    private Command command;
    private String subcommand;
    private String permission;
    private String description = "No description";

    public SubCommand(Command command, String subcommand, String permission) {
        this.command = command;
        this.subcommand = subcommand;
        this.permission = permission;
    }

    public abstract void execute(CommandSender commandSender);

}
