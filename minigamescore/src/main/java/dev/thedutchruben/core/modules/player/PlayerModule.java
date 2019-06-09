package dev.thedutchruben.core.modules.player;

import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import dev.thedutchruben.core.framework.player.PlayerData;
import dev.thedutchruben.core.framework.player.PlayerLoader;
import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.modules.player.listeners.PlayerJoinListener;
import dev.thedutchruben.core.modules.player.listeners.PlayerQuitListener;
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
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), MiniGamesCore.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), MiniGamesCore.getInstance());

        playerLoader = new PlayerLoader();
    }

    public MinigamesPlayer getMinigamesPlayer(Player player){
        return getMinigamesPlayer(player.getUniqueId());
    }

    public MinigamesPlayer getMinigamesPlayer(UUID uuid){
        return minigamesPlayers.get(uuid);
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
