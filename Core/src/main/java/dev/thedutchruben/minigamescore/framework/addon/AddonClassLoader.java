package dev.thedutchruben.minigamescore.framework.addon;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class AddonClassLoader extends URLClassLoader {
    private final Map<String, Class<?>> classes = new HashMap<>();
    private Addon addon;
    public AddonClassLoader( YamlConfiguration data, File path, ClassLoader parent) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, MalformedURLException {
        super(new URL[]{path.toURI().toURL()}, parent);
        Class<?> javaClass = null;
        try {
            String mainClass = data.getString("main");
            if (mainClass == null) {

            }
            javaClass = Class.forName(mainClass, true, this);

        } catch (Exception e) {

        }

        Class<? extends Addon> addonClass = null;
        try {
            addonClass = javaClass.asSubclass(Addon.class);
        } catch (ClassCastException e) {

        }

        addon = addonClass.getDeclaredConstructor().newInstance();


    }

    public Addon getAddon() {
        return addon;
    }
}
