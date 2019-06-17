package dev.thedutchruben.thesearch.framework.map;

import org.bukkit.Location;

import java.util.List;

public class Map {
    private Location spawn;
    private Location spawnMapLocation;
    private List<Location> headLocations;
    private int ammount;

    public Map(Location spawn, Location spawnMapLocation, List<Location> headLocations, int ammount) {
        this.spawn = spawn;
        this.spawnMapLocation = spawnMapLocation;
        this.headLocations = headLocations;
        this.ammount = ammount;
    }



    public Location getSpawn() {
        return spawn;
    }

    public Location getSpawnMapLocation() {
        return spawnMapLocation;
    }

    public List<Location> getHeadLocations() {
        return headLocations;
    }

    public int getAmmount() {
        return ammount;
    }
}
