package dev.thedutchruben.minigamescore.framework.player;

import dev.thedutchruben.minigamescore.modules.player.PlayerModule;

import java.util.UUID;

public class MinigamesPlayer {
    private UUID _id;
    private DataMap dataMap;




    public <D extends MiniGamesData> D getData(Class<D> type) {
        D d = type.cast(dataMap.get(type));
        if (d == null) {
            injectData(PlayerModule.create(type, this));
            return getData(type);
        }
        return d;
    }

    public <D extends MiniGamesData> void updateData(D data) {
        this.dataMap.put(data.getClass(), data);
    }

    public <D extends MiniGamesData> void injectData(D data) {
        this.dataMap.putIfAbsent(data.getClass(), data);
    }

    public <D extends MiniGamesData> boolean purgeData(Class<D> type) {
        return dataMap.remove(type) != null;
    }


}
