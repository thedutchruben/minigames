package dev.thedutchruben.core.framework.player.data;

import dev.thedutchruben.core.framework.level.Level;
import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import dev.thedutchruben.core.framework.player.PlayerData;
import lombok.AllArgsConstructor;
import net.minecraft.server.v1_13_R2.PacketPlayOutPlayerInfo;

@AllArgsConstructor
public class EcoData implements PlayerData {
    private double coins;



    public static EcoData create(MinigamesPlayer player) {

        return new EcoData(0);
    }

    public double getCoins() {
        return coins;
    }
}
