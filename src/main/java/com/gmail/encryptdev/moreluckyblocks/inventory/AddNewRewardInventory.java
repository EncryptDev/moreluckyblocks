package com.gmail.encryptdev.moreluckyblocks.inventory;

import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.Material;

/**
 * Created by EncryptDev
 */
public class AddNewRewardInventory extends AbstractInventory {

    public AddNewRewardInventory() {
        super("§eAdd a new reward", 27);
        fill();
        this.bukkitInventory.setItem(10, ItemCreator.getItem(Material.DIAMOND_HOE, "§eAdd a new structure handler"));
        this.bukkitInventory.setItem(11, ItemCreator.getItem(Material.SKULL_ITEM, "§eAdd a new mob handler"));
        this.bukkitInventory.setItem(13, ItemCreator.getItem(Material.PAPER, "§eHandler Manager"));
        this.bukkitInventory.setItem(15, ItemCreator.getItem(Material.APPLE, "§eAdd a new item handler"));
        this.bukkitInventory.setItem(16, ItemCreator.getItem(Material.ANVIL, "§eAdd a new falling block handler"));

    }
}
