package dev.thedutchruben.core.modules.friend;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.modules.friend.commands.FriendCommand;

import java.util.UUID;

public class FriendModule {

    public FriendModule() {

        registerCommands();
    }

    public void registerCommands(){
        MiniGamesCore.getInstance().getCommand("friend").setExecutor(new FriendCommand());

    }

    public void friendJoin(UUID uuid){
        MiniGamesCore.getInstance().getRedisDb().getPool().getResource().publish("friend-join",uuid.toString());
    }

    public void friendQuit(UUID uuid){
        MiniGamesCore.getInstance().getRedisDb().getPool().getResource().publish("friend-quit",uuid.toString());
    }
}
