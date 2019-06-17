package dev.thedutchruben.core.framework.player;


import dev.thedutchruben.core.framework.player.data.CommonData;
import dev.thedutchruben.core.framework.player.data.EcoData;
import dev.thedutchruben.core.framework.player.data.FriendData;
import dev.thedutchruben.core.framework.player.data.TheSearchData;
import dev.thedutchruben.core.modules.player.PlayerModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MinigamesPlayer {
    private UUID uuid;
    private DataMap data = new DataMap();

    public MinigamesPlayer(UUID uuid) {
        this.uuid = uuid;
        injectData(CommonData.create(this));
    }

    public UUID getUuid() {
        return uuid;
    }

    public <D extends PlayerData> D getData(Class<D> type) {
        if(type == null){
            getBukkitPlayer().kickPlayer("Player data could not load");
            return null;
        }
        D d = type.cast(data.get(type));
        if (d == null) {
            injectData(PlayerModule.create(type, this));
            return getData(type);
        }
        return d;
    }

    public <D extends PlayerData> void updateData(D data) {
        this.data.put(data.getClass(), data);
    }

    public <D extends PlayerData> void injectData(D data) {
        this.data.putIfAbsent(data.getClass(), data);
    }

    public <D extends PlayerData> boolean purgeData(Class<D> type) {
        return data.remove(type) != null;
    }

    public CommonData getCommonData(){
        return getData(CommonData.class);
    }

    public EcoData getEconomicData(){
        return getData(EcoData.class);
    }

    public TheSearchData getTheSearchData(){
        return getData(TheSearchData.class);
    }

    public FriendData getFriendData(){
        return getData(FriendData.class);
    }

    public Player getBukkitPlayer(){
        return Bukkit.getPlayer(uuid);
    }
}
