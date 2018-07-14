package com.gmail.encryptdev.moreluckyblocks.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by EncryptDev
 */
public class ChoosePotionInventory extends AbstractInventory {

    public ChoosePotionInventory() {
        super("§eMob Potions", 9);

        fill();
        bukkitInventory.setItem(0, ItemCreator.getItem(Material.POTION, "§eRegeneration", Arrays.asList("§eStatus: §4OFF")));
        bukkitInventory.setItem(1, ItemCreator.getItem(Material.POTION, "§eInvisibility", Arrays.asList("§eStatus: §4OFF")));
        bukkitInventory.setItem(2, ItemCreator.getItem(Material.POTION, "§eStrength", Arrays.asList("§eStatus: §4OFF")));
        bukkitInventory.setItem(3, ItemCreator.getItem(Material.POTION, "§eSpeed", Arrays.asList("§eStatus: §4OFF")));
        bukkitInventory.setItem(8, ItemCreator.getItem(Material.ARROW, "§eBack"));

    }
}
