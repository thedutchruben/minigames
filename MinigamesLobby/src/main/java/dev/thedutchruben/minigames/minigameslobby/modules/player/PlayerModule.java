package dev.thedutchruben.minigames.minigameslobby.modules.player;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.utils.GUIClass;
import dev.thedutchruben.minigames.minigameslobby.MinigamesLobby;
import dev.thedutchruben.minigames.minigameslobby.modules.player.listeners.MinigamesPlayerJoinListener;
import dev.thedutchruben.core.utils.ItemBuilder;
import dev.thedutchruben.minigames.minigameslobby.modules.player.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerModule {
    public PlayerModule(){
        Bukkit.getPluginManager().registerEvents(new MinigamesPlayerJoinListener(), MinigamesLobby.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), MinigamesLobby.getInstance());


    }

    public void giveLobbyItems(Player player){
        player.getInventory().setHeldItemSlot(4);
        player.getInventory().setItem(0,new ItemBuilder(Material.BOOK).name("&2Lobby's").make());
        player.getInventory().setItem(2,new ItemBuilder(Material.CHEST).name("&2Cosmetic's").make());
        player.getInventory().setItem(4,new ItemBuilder(Material.COMPASS).name("&2Game's").make());
        player.getInventory().setItem(8,new ItemBuilder(Material.PLAYER_HEAD).skullOwner(player.getName()).name("&2Player Info").make());

    }

    public GUIClass playerInfo(Player player){
        GUIClass guiClass = new GUIClass(54,"Player Info");
        guiClass.addItem(new ItemBuilder(Material.PLAYER_HEAD)
                .lore(ChatColor.GRAY + "Level : " + ChatColor.WHITE +MiniGamesCore.getInstance().getPlayerModule().getMinigamesPlayer(player).getCommonData().getLevel())
                .lore(ChatColor.GRAY + "XP : " + ChatColor.WHITE +MiniGamesCore.getInstance().getPlayerModule().getMinigamesPlayer(player).getCommonData().getXp())
                .skullOwner(player.getName())
                .name(player.getName()).make(),13);
        return guiClass;
    }
}
