package dev.thedutchruben.thesearch.modules.game.listeners;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.server.events.GameEndEvent;
import dev.thedutchruben.core.framework.server.events.GameStartEvent;
import dev.thedutchruben.thesearch.Thesearch;
import dev.thedutchruben.thesearch.framework.map.Map;
import dev.thedutchruben.thesearch.modules.game.runnables.GameEndRunnable;
import dev.thedutchruben.core.utils.SkullCreator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class GameStartListener implements Listener {
    @EventHandler
    public void onStart(GameStartEvent event){
        Map map =  Thesearch.getInstance().getMap();
        List<Location> locationList = map.getHeadLocations();
        locationList.forEach(location ->{
            location.getBlock().setType(Material.PLAYER_HEAD);
            SkullCreator.blockWithUrl(location.getBlock(),"http://textures.minecraft.net/texture/955d611a878e821231749b2965708cad942650672db09e26847a88e2fac2946");
        });
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.setWalkSpeed(0.3F);
            Thesearch.getInstance().getGameMode().getBossBar().addPlayer(onlinePlayer);
        }
        MiniGamesCore.getInstance().getPlayerModule().getMinigamesPlayers().values().forEach(minigamesPlayer -> Thesearch.getInstance().getGameMode().setScoreboard(minigamesPlayer));
        Bukkit.getScheduler().runTaskTimerAsynchronously(Thesearch.getInstance(),new GameEndRunnable(),0,20);

    }


    public static void setSkullUrl(Block block) {
        block.setType(Material.PLAYER_HEAD);
        Skull skullData = (Skull)block.getState();

        block.getState().update(true);
    }

}
