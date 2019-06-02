package dev.thedutchruben.minigames.minigameslobby.modules.player;

import dev.thedutchruben.minigames.minigameslobby.MinigamesLobby;
import dev.thedutchruben.minigames.minigameslobby.modules.player.listeners.MinigamesPlayerJoinListener;
import dev.thedutchruben.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerModule {

    public PlayerModule(){
        Bukkit.getPluginManager().registerEvents(new MinigamesPlayerJoinListener(), MinigamesLobby.getInstance());
    }

    public void giveLobbyItems(Player player){
        player.getInventory().setHeldItemSlot(4);
        player.getInventory().setItem(0,new ItemBuilder(Material.BOOK).name("&2Lobby's").make());
        player.getInventory().setItem(2,new ItemBuilder(Material.CHEST).name("&2Cosmetic's").make());
        player.getInventory().setItem(4,new ItemBuilder(Material.COMPASS).name("&2Game's").make());
        player.getInventory().setItem(8,new ItemBuilder(Material.PLAYER_HEAD).skullOwner(player.getName()).name("&2Player Info").make());

    }
}
