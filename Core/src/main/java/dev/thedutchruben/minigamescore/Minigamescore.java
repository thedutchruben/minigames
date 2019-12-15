package dev.thedutchruben.minigamescore;

import dev.thedutchruben.minigamescore.framework.addon.AddonManager;
import dev.thedutchruben.minigamescore.framework.commands.Command;
import dev.thedutchruben.minigamescore.framework.registery.Module;
import dev.thedutchruben.minigamescore.modules.player.PlayerModule;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;
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
    private AddonManager addonManager = new AddonManager();

    @Override
    public void onLoad() {
        minigamescore = this;
        put(PlayerModule.class);

        modules.values().forEach(Module::onLoad);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        modules.values().forEach(Module::onEnable);

        SimplePluginManager spm = (SimplePluginManager) this.getServer()
                .getPluginManager();

        Field f = null;
        try {
            f = SimplePluginManager.class.getDeclaredField("commandMap");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        SimpleCommandMap scm = null;
        try {
             scm = (SimpleCommandMap) f.get(spm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        scm.clearCommands();
        for (Module module : modules.values()) {
            for (Command command : module.getCommands()) {
                try {
                    System.out.println("Register command : " + command.getName());
                    scm.register("minigames", command);
                } catch(Exception e) {
                    e.printStackTrace();
                }


            }
        }
        addonManager.loadAddons();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        addonManager.disableAddons();
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
            abstractModule = (Module) constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        modules.put(aClass, abstractModule);
    }


}
