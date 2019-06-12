package dev.thedutchruben.core.modules.level;

import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.level.Level;
import dev.thedutchruben.core.modules.level.command.LevelCommand;

public class LevelModule {

    public LevelModule() {
        registerCommand();
    }

    public void registerCommand() {
        MiniGamesCore.getInstance().getCommand("level").setExecutor(new LevelCommand());
    }

    public Level getLevel(int level) {
        for (Level level1 : MiniGamesCore.getInstance().getLevels()) {
            if (level1.getLevel() == level) {
                return level1;
            }
        }
        return null;
    }

}
