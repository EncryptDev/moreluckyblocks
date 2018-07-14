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
        super(MessageTranslator.getInventoryName("mob-settings"), 27);

        fill();
        bukkitInventory.setItem(10, ItemCreator.getItem(Material.DIAMOND_CHESTPLATE, MessageTranslator.getItemName("mob-inventory-armor")));
        bukkitInventory.setItem(11, ItemCreator.getItem(Material.DIAMOND_SWORD, MessageTranslator.getItemName("mob-inventory-weapons")));
        bukkitInventory.setItem(12, ItemCreator.getItem(Material.NAME_TAG, MessageTranslator.getItemName("mob-inventory-name")));
        bukkitInventory.setItem(13, ItemCreator.getItem(Material.APPLE, MessageTranslator.getItemName("mob-inventory-attribute")));
        bukkitInventory.setItem(14, ItemCreator.getItem(Material.POTION, MessageTranslator.getItemName("mob-inventory-potion")));
        bukkitInventory.setItem(26, ItemCreator.getItem(Material.DIAMOND, MessageTranslator.getItemName("finish-item")));
    }
}
