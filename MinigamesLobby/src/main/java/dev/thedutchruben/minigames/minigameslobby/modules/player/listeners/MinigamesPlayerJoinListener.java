package dev.thedutchruben.minigames.minigameslobby.modules.player.listeners;

import dev.thedutchruben.framework.player.event.MinigamePlayerJoinEvent;
import dev.thedutchruben.framework.scoreboard.Scoreboard;
import dev.thedutchruben.minigames.minigameslobby.MinigamesLobby;
import dev.thedutchruben.utils.FireworkEffectPlayer;
import dev.thedutchruben.utils.Utils;
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
        Scoreboard scoreboard = new Scoreboard("&2&lMinigames Lobby");
        scoreboard.addLine("Coins:");
        scoreboard.addLine(String.valueOf(event.getMinigamesPlayer().getCoins()));
        scoreboard.addLine(" ");
        scoreboard.addLine("Rank:");
        scoreboard.addLine(event.getMinigamesPlayer().getNameColor() + event.getMinigamesPlayer().getMiniGamesRank().name());
        scoreboard.setTeamColor(event.getMinigamesPlayer().getNameColor());
        scoreboard.setTabPrefix("&f[%color%%level%&f] &4%name%".replace("%color%",event.getMinigamesPlayer().getNameColor().toString()).replace("%level%",event
                .getMinigamesPlayer().getLevel() + "").replace("%name%",event.getMinigamesPlayer().getBukkitPLayer().getName()));
        event.getMinigamesPlayer().setScoreboard(scoreboard);

        MinigamesLobby.getInstance().getPlayerModule().giveLobbyItems(event.getMinigamesPlayer().getBukkitPLayer());

        Utils.circle(event.getMinigamesPlayer().getBukkitPLayer().getLocation(),
                10,5,true ,true,8).forEach(location ->{
            FireworkEffectPlayer fireworkEffectPlayer = new FireworkEffectPlayer();
            fireworkEffectPlayer.setLocation(location);
            fireworkEffectPlayer.setPower(0);
            fireworkEffectPlayer.addEffect(FireworkEffect.builder().flicker(true).withColor(Color.AQUA).withColor(Color.PURPLE).build());
            fireworkEffectPlayer.setLifeTime(1);
            fireworkEffectPlayer.spawn();
        });
    }
}
