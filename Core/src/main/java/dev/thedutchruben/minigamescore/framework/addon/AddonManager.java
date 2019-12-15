package dev.thedutchruben.minigamescore.framework.addon;

import dev.thedutchruben.minigamescore.Minigamescore;
import lombok.NonNull;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class AddonManager {
    private List<Addon> addons = new ArrayList<>();

    public void loadAddons() {
        File f = new File(Minigamescore.getInstance().getDataFolder(), "addons");
        if (!f.exists() && !f.mkdirs()) {
            return;
        }
        Arrays.stream(Objects.requireNonNull(f.listFiles())).filter(x -> !x.isDirectory() && x.getName().endsWith(".jar")).forEach(this::loadAddon);
        enableAddons();
    }

    private void loadAddon(@NonNull File f) {
        Addon addon;
        AddonClassLoader addonClassLoader;
        try (JarFile jar = new JarFile(f)) {
            // try loading the addon
            // Get description in the addon.yml file
            YamlConfiguration data = addonDescription(jar);

            // Load the addon
            addonClassLoader = new AddonClassLoader(data, f, this.getClass().getClassLoader());

            // Get the addon itself
            addon = addonClassLoader.getAddon();
        } catch (Exception e) {
            // We couldn't load the addon, aborting.
            e.printStackTrace();
            return;
        }



        // Add it to the list of addons
        addons.remove(addon);
        addons.add(addon);


    }

    /**
     * Enables all the addons
     */
    public void enableAddons() {
        addons.forEach(this::enableAddon);

    }

    public void disableAddons() {
        addons.forEach(this::disableAddon);

    }


    /**
     * Enables an addon
     * @param addon addon
     */
    private void enableAddon(Addon addon) {
        addon.onEnable();
        System.out.println("Enable addon : " + addon.getName());
        addon.setState(Addon.State.ENABLED);
    }

    private void disableAddon(Addon addon) {
        addon.onDisable();
        System.out.println("Disable addon : " + addon.getName());
        addon.setState(Addon.State.DISABLED);
    }

    private YamlConfiguration addonDescription(@NonNull JarFile jar) throws IOException, InvalidConfigurationException {
        // Obtain the addon.yml file
        JarEntry entry = jar.getJarEntry("plugin.yml");
        // Open a reader to the jar
        BufferedReader reader = new BufferedReader(new InputStreamReader(jar.getInputStream(entry)));
        // Grab the description in the addon.yml file
        YamlConfiguration data = new YamlConfiguration();
        data.load(reader);
        return data;
    }
}
