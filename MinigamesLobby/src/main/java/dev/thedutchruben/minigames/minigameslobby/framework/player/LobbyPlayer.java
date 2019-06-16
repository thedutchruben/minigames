package dev.thedutchruben.minigames.minigameslobby.framework.player;


import dev.thedutchruben.core.utils.Holograms;
import org.bukkit.Material;

import javax.xml.stream.Location;
import java.util.Map;
import java.util.UUID;

public class LobbyPlayer {
    private Map<Location, Holograms> hologramsMap;
    private UUID uuid;
    private Material location;

    public LobbyPlayer(Map<Location, Holograms> hologramsMap, UUID uuid, Material location) {
        this.hologramsMap = hologramsMap;
        this.uuid = uuid;
        this.location = location;
    }

    public Map<Location, Holograms> getHologramsMap() {
        return hologramsMap;
    }

    public void setHologramsMap(Map<Location, Holograms> hologramsMap) {
        this.hologramsMap = hologramsMap;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Material getLocation() {
        return location;
    }

    public void setLocation(Material location) {
        this.location = location;
    }
}
