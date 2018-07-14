package com.gmail.encryptdev.moreluckyblocks.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.Material;

/**
 * Created by EncryptDev
 */
public class ChooseArmorTypeInventory extends AbstractInventory {

    public ChooseArmorTypeInventory() {
        super(MessageTranslator.getInventoryName("mob-armor"), 9);

        fill();

        bukkitInventory.setItem(0, ItemCreator.getItem(Material.GOLD_HELMET, MessageTranslator.getItemName("mob-armor-inventory-helmet")));
        bukkitInventory.setItem(1, ItemCreator.getItem(Material.GOLD_CHESTPLATE, MessageTranslator.getItemName("mob-armor-inventory-chestplate")));
        bukkitInventory.setItem(2, ItemCreator.getItem(Material.GOLD_LEGGINGS, MessageTranslator.getItemName("mob-armor-inventory-leggings")));
        bukkitInventory.setItem(3, ItemCreator.getItem(Material.GOLD_BOOTS, MessageTranslator.getItemName("mob-armor-inventory-boots")));
        bukkitInventory.setItem(8, ItemCreator.getItem(Material.ARROW, MessageTranslator.getItemName("page-back")));

    }
}
