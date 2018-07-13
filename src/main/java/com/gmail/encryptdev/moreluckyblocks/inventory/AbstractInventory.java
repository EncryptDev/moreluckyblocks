package com.gmail.encryptdev.moreluckyblocks.inventory;

import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public abstract class AbstractInventory {

    protected Inventory bukkitInventory;

    public AbstractInventory(String name, int size) {
        this.bukkitInventory = Bukkit.createInventory(null, size, name);
    }

    protected void fill() {
        this.fill(0, bukkitInventory.getSize(), ItemCreator.getItem(Material.STAINED_GLASS_PANE, "§0", 1, (byte) 10));
    }

    protected void fill(int from, int to, ItemStack filler) {
        for(int i = from; i < to; i++)
            bukkitInventory.setItem(i, filler);
    }

    public static void openInventory(Player player, AbstractInventory inventory) {
        player.openInventory(inventory.bukkitInventory);
    }

}
