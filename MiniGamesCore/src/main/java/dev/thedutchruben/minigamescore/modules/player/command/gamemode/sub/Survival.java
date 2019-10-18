package dev.thedutchruben.minigamescore.modules.player.command.gamemode.sub;

import dev.thedutchruben.minigamescore.framework.command.Command;
import dev.thedutchruben.minigamescore.framework.command.SubCommand;
import dev.thedutchruben.minigamescore.utils.Colors;
import dev.thedutchruben.minigamescore.utils.MessageUtil;
import dev.thedutchruben.minigamescore.utils.Replacement;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Survival extends SubCommand {
    public Survival(Command command, String subcommand) {
        super(command, subcommand);
        addAlias("0");
        addAlias("sur");
        addAlias("s");
    }

    @Override
    public void execute(CommandSender commandSender) {
        ((Player)commandSender).setGameMode(GameMode.SURVIVAL);
        MessageUtil.sendMessage((Player) commandSender, Colors.MESSAGE,"Je staat nu in gamemode {GAMEMODE}",true,new Replacement("{GAMEMODE}","Survival"));
    }
}
