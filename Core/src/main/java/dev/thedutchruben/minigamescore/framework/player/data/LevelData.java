package dev.thedutchruben.minigamescore.framework.player.data;

import dev.thedutchruben.minigamescore.framework.player.MiniGamesData;
import dev.thedutchruben.minigamescore.framework.player.MinigamesPlayer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.logging.Level;

@AllArgsConstructor
@Data
public class LevelData implements MiniGamesData {
    private int level;
    private double xp;
        public LevelData create(MinigamesPlayer minigamesPlayer){
            return new LevelData(1,0.0);
        }

}
