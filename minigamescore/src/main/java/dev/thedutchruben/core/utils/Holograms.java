package dev.thedutchruben.core.utils;

import java.util.ArrayList;
import java.util.List;

import dev.thedutchruben.core.MiniGamesCore;
import net.minecraft.server.v1_13_R2.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Holograms {

    private List<EntityArmorStand> entitylist = new ArrayList<EntityArmorStand>();
    private String[] Text;
    private Location location;
    private double DISTANCE = 0.25D;
    int count;

    public Holograms(String[] Text, Location location) {
        this.Text = Text;
        this.location = location;
        create();
    }


    public void showPlayerTemp(final Player p,int Time){
        showPlayer(p);
        Bukkit.getScheduler().runTaskLater(MiniGamesCore.getInstance(), new Runnable() {
            public void run() {
                hidePlayer(p);
            }
        }, Time);
    }


    public void showAllTemp(final Player p,int Time){
        showAll();
        Bukkit.getScheduler().runTaskLater(MiniGamesCore.getInstance(), new Runnable() {
            public void run() {
                hideAll();
            }
        }, Time);
    }

    public void showPlayer(Player p) {
        for (EntityArmorStand armor : entitylist) {
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armor);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public void hidePlayer(Player p) {
        for (EntityArmorStand armor : entitylist) {
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(armor.getId());
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);

        }
    }

    public void showAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (EntityArmorStand armor : entitylist) {
                PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armor);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    public void hideAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (EntityArmorStand armor : entitylist) {
                PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(armor.getId());
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    private void create() {
        for (String text : this.Text) {
            EntityArmorStand entity = new EntityArmorStand(((CraftWorld) this.location.getWorld()).getHandle(),this.location.getX(), this.location.getY(),this.location.getZ());
            IChatBaseComponent iChatBaseComponent = new ChatMessage(text);
            iChatBaseComponent.getChatModifier().setBold(true);

            entity.setCustomName(iChatBaseComponent);
            entity.setCustomNameVisible(true);
            entity.setInvisible(true);
            entity.setNoGravity(false);
            entitylist.add(entity);
            this.location.subtract(0, this.DISTANCE, 0);
            count++;
        }

        for (int i = 0; i < count; i++) {
            this.location.add(0, this.DISTANCE, 0);
        }
        this.count = 0;
    }
}
