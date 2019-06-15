package dev.thedutchruben.core.modules.game.commands;

import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.core.framework.server.events.GameEndEvent;
import dev.thedutchruben.core.modules.game.runnables.GameStartRunnable;
import dev.thedutchruben.core.modules.player.PlayerModule;
import dev.thedutchruben.core.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length <= 0){
            return false;
        }
        checkArgs(sender,args[0]);
        return false;
    }


    public void checkArgs(CommandSender commandSender,String args){
        switch (args.toLowerCase()){
            case "forcestart":
                forceStart(commandSender);
                break;
            case "setspawn":
                setSpawn((Player) commandSender);
                break;
            case "endgame":
                stop(commandSender);
                break;
        }
    }


    public void forceStart(CommandSender commandSender){
        GameStartRunnable.setTime(10);
        MessageUtil.broadCast("De game is geforcestart");
    }

    public void setSpawn(Player player){
        Bukkit.getWorlds().get(0).setSpawnLocation(player.getLocation());
        Game.getGame().setLobby(player.getLocation());
        Game.getGame().save();
        MessageUtil.sendMessage(player,"Spawn is gezet!");
    }

    public void stop(CommandSender commandSender){
        Bukkit.getServer().getPluginManager().callEvent(new GameEndEvent(Game.getGame(), PlayerModule.getMinigamesPlayer((Player) commandSender)));
        MessageUtil.broadCast("De game is geforcestopt");
    }
}
