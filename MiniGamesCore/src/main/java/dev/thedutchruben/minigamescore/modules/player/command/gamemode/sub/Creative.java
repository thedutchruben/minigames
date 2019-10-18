package dev.thedutchruben.minigamescore.modules.player.command.gamemode.sub;

import dev.thedutchruben.minigamescore.framework.command.Command;
import dev.thedutchruben.minigamescore.framework.command.SubCommand;
import dev.thedutchruben.minigamescore.utils.Colors;
import dev.thedutchruben.minigamescore.utils.MessageUtil;
import dev.thedutchruben.minigamescore.utils.Replacement;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Creative extends SubCommand {
    public Creative(Command command, String subcommand) {
        super(command, subcommand);
        addAlias("1");
        addAlias("crea");
        addAlias("c");
    }

    @Override
    public void execute(CommandSender commandSender) {
        ((Player)commandSender).setGameMode(GameMode.CREATIVE);
        MessageUtil.sendMessage((Player) commandSender, Colors.MESSAGE,"Je staat nu in gamemode {GAMEMODE}",true,new Replacement("{GAMEMODE}","Creative"));
    }
}
