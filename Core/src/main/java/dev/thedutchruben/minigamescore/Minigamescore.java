package dev.thedutchruben.minigamescore;

import dev.thedutchruben.minigamescore.framework.commands.Command;
import dev.thedutchruben.minigamescore.framework.registery.Module;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public final class Minigamescore extends JavaPlugin {

    private static Minigamescore minigamescore;
    private  LinkedHashMap<Class<? extends Module>, Module> modules = new LinkedHashMap<>();


    @Override
    public void onLoad() {
        minigamescore = this;
        modules.values().forEach(Module::onLoad);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        modules.values().forEach(Module::onEnable);

        for (Module module : modules.values()) {
            for (Command command : module.getCommands()) {
                try {
                    final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

                    bukkitCommandMap.setAccessible(true);
                    CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
                    commandMap.clearCommands();
                    commandMap.register("minigames", command);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        modules.values().forEach(Module::onDisable);

    }


    public static Minigamescore getInstance(){
        return minigamescore;
    }

    public static <M extends Module> M getModule(Class<M> aClass) {
        return (M) Minigamescore.getInstance().modules.get(aClass);
    }

    public void put(Class<? extends Module> aClass) {
        Constructor constructor = null;
        Module abstractModule = null;
        try {
            constructor = aClass.getConstructor();
            abstractModule = (Module) constructor.newInstance( this);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        modules.put(aClass, abstractModule);
    }


}
