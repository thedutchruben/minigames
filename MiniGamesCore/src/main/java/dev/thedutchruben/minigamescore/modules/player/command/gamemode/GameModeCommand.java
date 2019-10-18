package dev.thedutchruben.minigamescore.modules.player.command.gamemode;

import dev.thedutchruben.minigamescore.framework.command.Command;
import dev.thedutchruben.minigamescore.modules.player.command.gamemode.sub.Creative;
import dev.thedutchruben.minigamescore.modules.player.command.gamemode.sub.Survival;
import org.bukkit.command.CommandSender;

public class GameModeCommand extends Command{
    public GameModeCommand(String command) {
        super(command, command);
        addSubCommand(new Creative(this,"creative"));
        addSubCommand(new Survival(this,"survival"));
    }

    @Override
    public void executeDefault(CommandSender commandSender, String[] args) {

    }
}
