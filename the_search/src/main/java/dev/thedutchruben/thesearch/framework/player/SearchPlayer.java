package dev.thedutchruben.thesearch.framework.player;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SearchPlayer {

    private UUID uuid;
    private List<Location> locations = new ArrayList<>();

    public SearchPlayer(UUID uuid, List<Location> locations) {
        this.uuid = uuid;
        this.locations = locations;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<Location> getLocations() {
        return locations;
    }



}


