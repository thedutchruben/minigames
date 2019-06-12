package dev.thedutchruben.core.modules.level.command;

import dev.thedutchruben.core.framework.level.Level;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class LevelCommand implements CommandExecutor , TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if(args.length >0){
                execute(sender,args);
            }
        return false;
    }

    public void execute(CommandSender commandSender,String[] args){
        switch (args[0].toLowerCase()){
            case "create":
                createLevels(commandSender,args);
            default:
        }
    }

    public void createLevels(CommandSender commandSender,String[] args){
        int startInt = Integer.valueOf(args[1]);
        int endInt = Integer.valueOf(args[2]);
        for(int integer = startInt; integer <= endInt;integer++){
            commandSender.sendMessage("Level " + integer + " created");
            new Level(integer,1);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
