package dev.thedutchruben.minigamescore.modules.player;

import dev.thedutchruben.minigamescore.Minigamescore;
import dev.thedutchruben.minigamescore.framework.commands.Command;
import dev.thedutchruben.minigamescore.framework.player.MiniGamesData;
import dev.thedutchruben.minigamescore.framework.player.MinigamesPlayer;
import dev.thedutchruben.minigamescore.framework.registery.Module;
import dev.thedutchruben.minigamescore.modules.player.commands.FlyCommand;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class PlayerModule extends Module {

    @Override
    public void onLoad() {
        System.out.println("load playermodle");
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public Set<Command> getCommands() {
        return new HashSet<>() {{
            add(new FlyCommand());
        }};
    }


    /**
     * Convenience method to create {@link MiniGamesData} using a {@code public static D create(MinigamesPlayer)} method
     *
     * @param clazz  The Class
     * @param player The Player
     * @param <D>    Data type
     * @return Created data
     */
    public static <D extends MiniGamesData> D create(Class<D> clazz, MinigamesPlayer player) {
        try {
            Method method = clazz.getMethod("create", MinigamesPlayer.class);
            return (D) method.invoke(null, player);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
