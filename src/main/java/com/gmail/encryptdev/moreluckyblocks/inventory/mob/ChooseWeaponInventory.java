package com.gmail.encryptdev.moreluckyblocks.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by EncryptDev
 */
public class ChooseWeaponInventory extends AbstractInventory {

    public ChooseWeaponInventory() {
        super("§eMob Weapons", 9);
        fill();
        bukkitInventory.setItem(0, ItemCreator.getItem(Material.GOLD_SWORD, "§eMain Hand"));
        bukkitInventory.setItem(1, ItemCreator.getItem(Material.GOLD_HOE, "§eOff Hand",
                Arrays.asList("§4Only available in version 1.9 or higher")));
        bukkitInventory.setItem(8, ItemCreator.getItem(Material.ARROW, "§eBack"));
    }
}
