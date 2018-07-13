package com.gmail.encryptdev.moreluckyblocks;

import com.gmail.encryptdev.moreluckyblocks.commands.CommandLuckyBlock;
import com.gmail.encryptdev.moreluckyblocks.json.JsonLoader;
import com.gmail.encryptdev.moreluckyblocks.listener.LuckyBlockBreakListener;
import com.gmail.encryptdev.moreluckyblocks.listener.LuckyBlockPlaceListener;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.IRewardHandler;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.SpawnItemHandler;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.SpawnMobHandler;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.StructureHandler;
import com.gmail.encryptdev.moreluckyblocks.structure.Structure;
import com.gmail.encryptdev.moreluckyblocks.structure.StructureLoader;
import com.gmail.encryptdev.moreluckyblocks.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Created by EncryptDev
 */
public class MoreLuckyBlocks extends JavaPlugin {

    private static MoreLuckyBlocks instance;

    private JsonLoader jsonLoader;
    private LuckyBlockManager luckyBlockManager;
    private StructureLoader structureLoader;

    @Override
    public void onEnable() {
        instance = this;

        if(!new File("messages.json").exists())
            saveResource("messages.json", false);
        if(!new File("settings.json").exists())
            saveResource("settings.json", false);

        this.jsonLoader = new JsonLoader();
        this.jsonLoader.load();
        this.luckyBlockManager = new LuckyBlockManager(this.jsonLoader);
        this.luckyBlockManager.init();
        this.structureLoader = new StructureLoader();
        this.structureLoader.init();

        this.registerSerializableClasses();

        this.registerListener();
        this.getCommand("lb").setExecutor(new CommandLuckyBlock());

        Log.info("Plugin started");
        Log.info("Developer: EncryptDev");
        Log.info("Version: " + this.getDescription().getVersion());

    }

    public StructureLoader getStructureLoader() {
        return structureLoader;
    }

    public JsonLoader getJsonLoader() {
        return jsonLoader;
    }

    private void registerSerializableClasses() {
        registerRewardHandler(SpawnItemHandler.class);
        registerRewardHandler(SpawnMobHandler.class);
        registerRewardHandler(StructureHandler.class);

        ConfigurationSerialization.registerClass(Structure.class);
        ConfigurationSerialization.registerClass(SpawnMobHandler.MobSettings.class);
        ConfigurationSerialization.registerClass(SpawnMobHandler.MobEquipment.class);
    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new LuckyBlockPlaceListener(), this);
        pluginManager.registerEvents(new LuckyBlockBreakListener(), this);
    }

    public void registerRewardHandler(Class<? extends IRewardHandler> aClass) {
        ConfigurationSerialization.registerClass(aClass);
    }

    public static MoreLuckyBlocks getInstance() {
        return instance;
    }
}
