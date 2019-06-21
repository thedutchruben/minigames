package dev.thedutchruben.core.modules.friend.commands;

import dev.thedutchruben.core.MiniGamesCore;
import org.apache.commons.lang.Validate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FriendCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return false;
    }

    public void subcommand(CommandSender commandSender,String[] args){

    }
}
