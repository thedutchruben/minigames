package dev.thedutchruben.core.modules.friend;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.friend.Friend;
import dev.thedutchruben.core.modules.friend.commands.FriendCommand;
import dev.thedutchruben.core.modules.player.PlayerModule;
import dev.thedutchruben.core.utils.MessageUtil;
import org.bukkit.Bukkit;
import redis.clients.jedis.JedisPubSub;

import java.util.Optional;
import java.util.UUID;

public class FriendModule {

    public FriendModule() {

        registerCommands();
        subScribe();
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

    public void subScribe(){
        new Thread("Friend Redis Subscriber"){
            @Override
            public void run(){
                String[] channels = {"friend-join", "friend-quit"};
                MiniGamesCore.getInstance().getRedisDb().getPool().getResource().subscribe(new JedisPubSub(){
                    @Override
                    public void onMessage(String channel, String message) {
                        switch (channel){
                            case "friend-join":
                                MiniGamesCore.getInstance().getPlayerModule().getMinigamesPlayers().values().forEach(minigamesPlayer -> {
                                   Optional<Friend> optional = minigamesPlayer.getFriendData().getFriends().stream().filter(friend -> friend.getUuid().toString().equalsIgnoreCase(message)).findAny();
                                    if(optional.isPresent()){
                                        MessageUtil.sendMessage(Bukkit.getPlayer(minigamesPlayer.getUuid()),"[FRIEND] .... Joined the server!");
                                    }
                                });
                                return;
                            case "friend-quit":
                                MiniGamesCore.getInstance().getPlayerModule().getMinigamesPlayers().values().forEach(minigamesPlayer -> {
                                    Optional<Friend> optional = minigamesPlayer.getFriendData().getFriends().stream().filter(friend -> friend.getUuid().toString().equalsIgnoreCase(message)).findAny();
                                    if(optional.isPresent()){
                                        MessageUtil.sendMessage(Bukkit.getPlayer(minigamesPlayer.getUuid()),"[FRIEND] .... Quit the server!");
                                    }
                                });
                                return;

                        }
                    }
                }, channels);
            }
        };
    }
}
