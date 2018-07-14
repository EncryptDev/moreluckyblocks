package com.gmail.encryptdev.moreluckyblocks;

import com.gmail.encryptdev.moreluckyblocks.commands.CommandLuckyBlock;
import com.gmail.encryptdev.moreluckyblocks.json.JsonLoader;
import com.gmail.encryptdev.moreluckyblocks.listener.ChatListener;
import com.gmail.encryptdev.moreluckyblocks.listener.LuckyBlockBreakListener;
import com.gmail.encryptdev.moreluckyblocks.listener.LuckyBlockPlaceListener;
import com.gmail.encryptdev.moreluckyblocks.listener.inventory.AddNewRewardInventoryListener;
import com.gmail.encryptdev.moreluckyblocks.listener.inventory.CounterInventoryListener;
import com.gmail.encryptdev.moreluckyblocks.listener.inventory.ListInventoryListener;
import com.gmail.encryptdev.moreluckyblocks.listener.inventory.PutInventoryListener;
import com.gmail.encryptdev.moreluckyblocks.listener.inventory.mob.*;
import com.gmail.encryptdev.moreluckyblocks.mob.MobCacheManager;
import com.gmail.encryptdev.moreluckyblocks.mob.MobSettings;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.*;
import com.gmail.encryptdev.moreluckyblocks.structure.Structure;
import com.gmail.encryptdev.moreluckyblocks.structure.StructureLoader;
import com.gmail.encryptdev.moreluckyblocks.util.Log;
import org.bukkit.Bukkit;
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
    private MobCacheManager mobCacheManager;
    private StructureLoader structureLoader;

    @Override
    public void onEnable() {
        instance = this;

        if (!new File("messages.json").exists())
            saveResource("messages.json", false);
        if (!new File("settings.json").exists())
            saveResource("settings.json", false);

        this.jsonLoader = new JsonLoader();
        this.jsonLoader.load();
        this.luckyBlockManager = new LuckyBlockManager(this.jsonLoader);
        this.luckyBlockManager.init();
        this.structureLoader = new StructureLoader();
        this.structureLoader.init();
        this.mobCacheManager = new MobCacheManager();

        this.registerSerializableClasses();

        this.registerListener();
        this.getCommand("lb").setExecutor(new CommandLuckyBlock());

        Log.info("Plugin started");
        Log.info("Developer: EncryptDev");
        Log.info("Version: " + this.getDescription().getVersion());

    }

    public JsonLoader getJsonLoader() {
        return jsonLoader;
    }

    private void registerSerializableClasses() {
        registerRewardHandler(SpawnItemHandler.class);
        registerRewardHandler(SpawnMobHandler.class);
        registerRewardHandler(StructureHandler.class);
        registerRewardHandler(ExecuteCommandHandler.class);

        ConfigurationSerialization.registerClass(Structure.class);
        ConfigurationSerialization.registerClass(MobSettings.class);
    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new LuckyBlockPlaceListener(), this);
        pluginManager.registerEvents(new LuckyBlockBreakListener(), this);
        pluginManager.registerEvents(new MobSettingsInventoryListener(mobCacheManager, luckyBlockManager), this);
        pluginManager.registerEvents(new ChatListener(luckyBlockManager, mobCacheManager), this);
        pluginManager.registerEvents(new AddNewRewardInventoryListener(luckyBlockManager), this);
        pluginManager.registerEvents(new ListInventoryListener(mobCacheManager), this);
        pluginManager.registerEvents(new PutInventoryListener(mobCacheManager), this);
        pluginManager.registerEvents(new MobSettingsInventoryListener(mobCacheManager, luckyBlockManager), this);
        pluginManager.registerEvents(new ChooseArmorTypeInventoryListener(), this);
        pluginManager.registerEvents(new ChooseWeaponInventoryListener(), this);
        pluginManager.registerEvents(new ChooseAttributeInventoryListener(), this);
        pluginManager.registerEvents(new CounterInventoryListener(mobCacheManager), this);
        pluginManager.registerEvents(new ChoosePotionInventoryListener(mobCacheManager), this);
    }

    public void registerRewardHandler(Class<? extends IRewardHandler> aClass) {
        ConfigurationSerialization.registerClass(aClass);
    }

    public static MoreLuckyBlocks getInstance() {
        return instance;
    }
}
