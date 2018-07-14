package com.gmail.encryptdev.moreluckyblocks.inventory;

import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.Material;

/**
 * Created by EncryptDev
 */
public class CounterInventory extends AbstractInventory {

    public CounterInventory(String name) {
        super("§eCounter | " + name, 27);

        bukkitInventory.setItem(9, ItemCreator.getItem(Material.ARROW, "§5§l-25"));
        bukkitInventory.setItem(10, ItemCreator.getItem(Material.ARROW, "§5§l-10"));
        bukkitInventory.setItem(11, ItemCreator.getItem(Material.ARROW, "§5§l-1"));
        bukkitInventory.setItem(12, ItemCreator.getItem(Material.ARROW, "§5§l-0.1"));
        bukkitInventory.setItem(13, ItemCreator.getItem(Material.GOLD_NUGGET, "§e" + name + ": 0.0"));
        bukkitInventory.setItem(14, ItemCreator.getItem(Material.ARROW, "§5§l+0.1"));
        bukkitInventory.setItem(15, ItemCreator.getItem(Material.ARROW, "§5§l+1"));
        bukkitInventory.setItem(16, ItemCreator.getItem(Material.ARROW, "§5§l+10"));
        bukkitInventory.setItem(17, ItemCreator.getItem(Material.ARROW, "§5§l+25"));

        bukkitInventory.setItem(26, ItemCreator.getItem(Material.DIAMOND, "§eFinish"));
    }
}
