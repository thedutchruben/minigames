package dev.thedutchruben.minigames.minigameslobby.modules.serversigns.listeners;

import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.minigames.minigameslobby.MinigamesLobby;
import dev.thedutchruben.core.utils.BungeeUtil;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignClickListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock().getType() == Material.WALL_SIGN) {
                Sign s = (Sign) e.getClickedBlock().getState();
                Game game = MinigamesLobby.getInstance().getServersignModule().getGames().stream().filter(game1 -> game1.getServerName().equalsIgnoreCase(s.getLine(2))).findFirst().get();
                if (game.getGameState().isCanJoin()) {
                    BungeeUtil.send(e.getPlayer(), s.getLine(2));
                }
            }
        }
    }
}
