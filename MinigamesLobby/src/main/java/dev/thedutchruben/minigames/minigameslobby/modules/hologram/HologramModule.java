package dev.thedutchruben.minigames.minigameslobby.modules.hologram;

import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import dev.thedutchruben.core.modules.player.PlayerModule;
import dev.thedutchruben.core.utils.Holograms;
import dev.thedutchruben.core.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class HologramModule {

    private List<Holograms> holograms;

    public void setUpHolos(Player player){
        setUpBasicStatsHologram(player);
    }


    public void setUpBasicStatsHologram(Player player){
        MinigamesPlayer minigamesPlayer = PlayerModule.getMinigamesPlayer(player);
        Holograms holograms = new Holograms(Arrays.asList(
                MessageUtil.sendCenteredMessage("-----------> Stats <-----------"),
                MessageUtil.sendCenteredMessage("Coins : " + minigamesPlayer.getEconomicData().getCoins()),
                MessageUtil.sendCenteredMessage("Level : " + minigamesPlayer.getCommonData().getLevel()))

                ,new Location(Bukkit.getWorlds().get(0),0,0,0));
        holograms.hidePlayer(player);
    }

    public void setUpTop10Wins(){

    }
}
