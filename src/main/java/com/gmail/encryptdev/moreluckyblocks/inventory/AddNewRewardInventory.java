package com.gmail.encryptdev.moreluckyblocks.inventory;

import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.Material;

/**
 * Created by EncryptDev
 */
public class AddNewRewardInventory extends AbstractInventory {

    public AddNewRewardInventory() {
        super(MessageTranslator.getInventoryName("add-new-reward"), 27);
        fill();
        this.bukkitInventory.setItem(10, ItemCreator.getItem(Material.DIAMOND_HOE, MessageTranslator.getItemName("add-structure")));
        this.bukkitInventory.setItem(11, ItemCreator.getItem(Material.SKULL_ITEM, MessageTranslator.getItemName("add-new-mob")));
        this.bukkitInventory.setItem(12, ItemCreator.getItem(Material.APPLE, MessageTranslator.getItemName("add-new-item")));

    }
}
