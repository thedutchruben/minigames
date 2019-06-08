package dev.thedutchruben.framework.player;


import dev.thedutchruben.framework.player.data.CommonData;
import dev.thedutchruben.modules.player.PlayerModule;

import java.util.UUID;

public class MinigamesPlayer {

    private UUID uuid;
    private DataMap data = new DataMap();

    public MinigamesPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public <D extends PlayerData> D getData(Class<D> type) {
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

}
