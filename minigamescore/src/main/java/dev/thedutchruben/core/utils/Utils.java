package dev.thedutchruben.core.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Utils {
    public static Entity[] getNearbyEntities(Location l, int radius) {
        int chunkRadius = radius < 16 ? 1 : (radius - radius % 16) / 16;
        HashSet<Entity> radiusEntities = new HashSet<>();
        for (int chX = 0 - chunkRadius; chX <= chunkRadius; chX++) {
            for (int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++) {
                int x = (int) l.getX();
                int y = (int) l.getY();
                int z = (int) l.getZ();
                for (Entity e : new Location(l.getWorld(), x + chX * 16, y, z + chZ * 16).getChunk().getEntities()) {
                    if ((e.getLocation().distance(l) <= radius) && (e.getLocation().getBlock() != l.getBlock())) {
                        radiusEntities.add(e);
                    }
                }
            }
        }
        return (Entity[]) radiusEntities.toArray(new Entity[radiusEntities.size()]);
    }


    public static List<Location> circle(Location loc, Integer r, Integer h, Boolean hollow, Boolean sphere, int plus_y) {
        List<Location> circleblocks = new ArrayList<>();

        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();
        for (int x = cx - r.intValue(); x <= cx + r.intValue(); x++) {
            for (int z = cz - r.intValue(); z <= cz + r.intValue(); z++) {
                for (int y = sphere.booleanValue() ? cy - r.intValue() : cy; y < (sphere.booleanValue() ? cy + r.intValue() : cy + h.intValue()); y++) {
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere.booleanValue() ? (cy - y) * (cy - y) : 0);
                    if ((dist < r.intValue() * r.intValue()) && ((!hollow.booleanValue()) || (dist >= (r.intValue() - 1) * (r.intValue() - 1)))) {
                        Location l = new Location(loc.getWorld(), x, y + plus_y, z);
                        circleblocks.add(l);
                    }
                }
            }
        }
        return circleblocks;
    }


    public static Block igniteBlock(Block b)
    {
        HashSet<Block> relatives = new HashSet<>();
        relatives.add(b.getRelative(BlockFace.UP));
        relatives.add(b.getRelative(BlockFace.DOWN));
        relatives.add(b.getRelative(BlockFace.EAST));
        relatives.add(b.getRelative(BlockFace.WEST));
        relatives.add(b.getRelative(BlockFace.NORTH));
        relatives.add(b.getRelative(BlockFace.SOUTH));
        for (Block b1 : relatives) {
            if ((b1.getType() == Material.AIR) || (b1.getType() == Material.SNOW)) {
                b1.setType(Material.FIRE);
            }
        }
        return b;
    }

}
