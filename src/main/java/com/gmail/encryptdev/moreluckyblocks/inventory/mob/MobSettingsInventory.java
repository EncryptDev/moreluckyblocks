package com.gmail.encryptdev.moreluckyblocks.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.Material;

/**
 * Created by EncryptDev
 */
public class MobSettingsInventory extends AbstractInventory {

    public MobSettingsInventory() {
        super("§eMob Settings", 27);

        fill();
        bukkitInventory.setItem(10, ItemCreator.getItem(Material.DIAMOND_CHESTPLATE, "§eMob Armor"));
        bukkitInventory.setItem(11, ItemCreator.getItem(Material.DIAMOND_SWORD, "§eMob Weapons"));
        bukkitInventory.setItem(12, ItemCreator.getItem(Material.NAME_TAG, "§eMob Name"));
        bukkitInventory.setItem(13, ItemCreator.getItem(Material.APPLE, "§eMob Attribute"));
        bukkitInventory.setItem(14, ItemCreator.getItem(Material.POTION, "§eMob Potion"));
        bukkitInventory.setItem(26, ItemCreator.getItem(Material.DIAMOND, "§eFinish"));
    }
}
