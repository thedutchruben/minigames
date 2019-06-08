package dev.thedutchruben.modules.player;

import dev.thedutchruben.framework.player.MinigamesPlayer;
import dev.thedutchruben.framework.player.PlayerData;
import dev.thedutchruben.framework.player.PlayerLoader;
import dev.thedutchruben.minigamescore;
import dev.thedutchruben.modules.player.listeners.PlayerJoinListener;
import dev.thedutchruben.modules.player.listeners.PlayerQuitListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerModule {
    @Getter private Map<UUID,MinigamesPlayer> minigamesPlayers;
    @Getter private PlayerLoader playerLoader;
    public PlayerModule(){
        minigamesPlayers = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), minigamescore.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), minigamescore.getInstance());

        playerLoader = new PlayerLoader();
    }


    public static MinigamesPlayer getMinigamesPlayer(Player player){
        return minigamescore.getInstance().getPlayerModule().minigamesPlayers.get(player.getUniqueId());
    }

    public static <D extends PlayerData> D create(Class<D> clazz, MinigamesPlayer player) {
        try {
            Method method = clazz.getMethod("create", MinigamesPlayer.class);
            return (D) method.invoke(null, player);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
