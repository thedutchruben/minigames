package dev.thedutchruben.minigamescore.modules.player.commands;

import dev.thedutchruben.minigamescore.framework.commands.Command;
import dev.thedutchruben.minigamescore.utils.Colors;
import dev.thedutchruben.minigamescore.utils.MessageUtil;
import dev.thedutchruben.minigamescore.utils.Replacement;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class FlyCommand extends Command {
    public FlyCommand() {
        super("fly", "fly");
        setAliases(Arrays.asList("vlieg", "flieeeee"));
        setDefaultList(false);
    }

    @Override
    public void executeDefault(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        player.setAllowFlight(!player.getAllowFlight());
        player.setFlying(!player.isFlying());

        MessageUtil.sendMessage(player, Colors.MESSAGE,"Je fly staat nu <FLYSTATE>",true, new Replacement("<FLYSTATE>",player.isFlying()+""));
    }
}
