package com.gmail.encryptdev.moreluckyblocks.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import org.bukkit.Material;

/**
 * Created by EncryptDev
 */
public class ChooseArmorTypeInventory extends AbstractInventory {

    public ChooseArmorTypeInventory() {
        super("§eMob Armor", 9);

        fill();

        bukkitInventory.setItem(0, ItemCreator.getItem(Material.GOLD_HELMET, "§eHelmet"));
        bukkitInventory.setItem(1, ItemCreator.getItem(Material.GOLD_CHESTPLATE, "§eChestplate"));
        bukkitInventory.setItem(2, ItemCreator.getItem(Material.GOLD_LEGGINGS, "§eLeggings"));
        bukkitInventory.setItem(3, ItemCreator.getItem(Material.GOLD_BOOTS, "§eBoots"));
        bukkitInventory.setItem(8, ItemCreator.getItem(Material.ARROW, "§eBack"));

    }
}
