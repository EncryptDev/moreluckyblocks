package com.gmail.encryptdev.moreluckyblocks.util;

import com.gmail.encryptdev.moreluckyblocks.MoreLuckyBlocks;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by EncryptDev
 */
public class MLBFileManager {

    private File file;
    private File dir;
    private FileConfiguration configuration;

    /**
     * Constructor with a subfolder and a file name
     *
     * @param fileName
     * @param subFolder
     */
    public MLBFileManager(String fileName, String subFolder) {
        if (subFolder.equals("none")) {
            this.dir = new File(MoreLuckyBlocks.getInstance().getDataFolder().getAbsolutePath());
            if (!this.dir.exists())
                this.dir.mkdirs();
            this.file = new File(dir + "/" + fileName + ".yml");
        } else {
            this.dir = new File(MoreLuckyBlocks.getInstance().getDataFolder().getAbsolutePath() + "/" + subFolder);
            if (!this.dir.exists())
                this.dir.mkdirs();
            this.file = new File(dir + "/" + fileName + ".yml");
        }
        if (!this.file.exists())
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        this.configuration = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Constructor with a filename
     * subfolder is "none"
     *
     * @param fileName
     */
    public MLBFileManager(String fileName) {
        this(fileName, "none");
    }

    public void copyDefault() {
        this.configuration.options().copyDefaults(true);
    }

    public void addDefault(String path, Object object) {
        this.configuration.addDefault(path, object);
    }

    public void set(String path, Object object) {
        this.configuration.set(path, object);
        this.save();
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return this.configuration.getConfigurationSection(path);
    }

    public boolean contains(String path) {
        return this.configuration.contains(path);
    }

    public Object get(String path) {
        return this.configuration.get(path);
    }

    /**
     * Get every type you want
     *
     * @param path
     * @param type
     * @param <T>
     * @return
     */
    public <T> T get(String path, Class<T> type) {
        if (this.configuration.get(path) == null)
            return null;

        return (T) this.configuration.get(path);
    }

    public void save() {
        try {
            this.configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
