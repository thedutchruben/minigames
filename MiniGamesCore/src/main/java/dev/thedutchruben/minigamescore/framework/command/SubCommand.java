package dev.thedutchruben.minigamescore.framework.command;

import lombok.Data;
import org.bukkit.command.CommandSender;

@Data
public abstract class SubCommand {
    private String command;
    private String permission;
    private String description = "No desciption";
    public SubCommand(String command, String permission) {
        this.command = command;
        this.permission = permission;
    }

    public abstract void execute(CommandSender commandSender);

}
