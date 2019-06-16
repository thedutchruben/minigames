package dev.thedutchruben.minigames.minigameslobby.modules.hologram;

import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import dev.thedutchruben.core.modules.player.PlayerModule;
import dev.thedutchruben.core.utils.Holograms;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class HologramModule {


    public void setUpHolos(Player player){
        setUpBasicStatsHologram(player);
        setTheSearchHoloGram(player);
    }


    public void setUpBasicStatsHologram(Player player){
        MinigamesPlayer minigamesPlayer = PlayerModule.getMinigamesPlayer(player);
        Holograms holograms = new Holograms(Arrays.asList( "<---------> Stats <--------->",
                "Coins : " + minigamesPlayer.getEconomicData().getCoins(),
                "Level : " + minigamesPlayer.getCommonData().getLevel(),
                "Online time : " + minigamesPlayer.getCommonData().getTotalPlayTime())
               ,(new Location(Bukkit.getWorlds().get(0),-1261.450,15,754.207)));
        holograms.showPlayer(player);
    }

    public void setTheSearchHoloGram(Player player){
        MinigamesPlayer minigamesPlayer = PlayerModule.getMinigamesPlayer(player);
        Holograms holograms = new Holograms(Arrays.asList(
                "<---------> The Search Stats <--------->",
                "Gespeelde games : " + minigamesPlayer.getTheSearchData().getGamesPlayed(),
                "Wins : " + minigamesPlayer.getTheSearchData().getGamesWon())
                ,new Location(Bukkit.getWorlds().get(0),-1239,15,745) );
        holograms.showPlayer(player);
    }

    public void setUpTop10Wins(){

    }
}
