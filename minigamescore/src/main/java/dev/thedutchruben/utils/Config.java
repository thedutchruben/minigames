package dev.thedutchruben.utils;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

@Getter
public class Config {
    private FileConfiguration configuration;
    private final File file;

    public Config(Plugin plugin, String fileName) {
        this.file = new File(plugin.getDataFolder(), fileName);
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
        save();
    }

    public void save() {
        try {
            this.configuration.save(this.file);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING,e.getMessage());
        }
    }

    public void reload() {
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }
}
