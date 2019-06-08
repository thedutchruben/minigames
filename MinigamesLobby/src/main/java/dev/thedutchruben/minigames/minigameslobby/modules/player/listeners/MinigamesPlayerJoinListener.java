package dev.thedutchruben.minigames.minigameslobby.modules.player.listeners;

import dev.thedutchruben.core.framework.player.event.MinigamePlayerJoinEvent;
import dev.thedutchruben.core.framework.scoreboard.Scoreboard;
import dev.thedutchruben.minigames.minigameslobby.MinigamesLobby;
import dev.thedutchruben.core.utils.FireworkEffectPlayer;
import dev.thedutchruben.core.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class MinigamesPlayerJoinListener implements Listener {


    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(MinigamePlayerJoinEvent event){
        Player player = Bukkit.getPlayer(event.getMinigamesPlayer().getUuid());
        player.setLevel(event.getMinigamesPlayer().getLevel());

        MinigamesLobby.getInstance().getPlayerModule().giveLobbyItems(event.getMinigamesPlayer().getBukkitPLayer());

        Utils.circle(event.getMinigamesPlayer().getBukkitPLayer().getLocation(),
                10,5,false ,false,8).forEach(location ->{
            FireworkEffectPlayer fireworkEffectPlayer = new FireworkEffectPlayer();
            fireworkEffectPlayer.setLocation(location);
            fireworkEffectPlayer.setPower(0);
            fireworkEffectPlayer.addEffect(FireworkEffect.builder().flicker(true).withColor(Color.AQUA).withColor(Color.PURPLE).build());
            fireworkEffectPlayer.setLifeTime(1);
            fireworkEffectPlayer.spawn();
        });
    }
}
