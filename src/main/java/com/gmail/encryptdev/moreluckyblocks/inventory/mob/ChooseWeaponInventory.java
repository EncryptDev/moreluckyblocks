package com.gmail.encryptdev.moreluckyblocks.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.Material;

/**
 * Created by EncryptDev
 */
public class ChooseWeaponInventory extends AbstractInventory {

    public ChooseWeaponInventory() {
        super(MessageTranslator.getInventoryName("mob-weapons"), 9);
        fill();
        bukkitInventory.setItem(0, ItemCreator.getItem(Material.GOLD_SWORD, MessageTranslator.getItemName("mob-weapon-inventory-main-hand")));
        bukkitInventory.setItem(1, ItemCreator.getItem(Material.GOLD_HOE, MessageTranslator.getItemName("mob-weapon-inventory-off-hand")));
        bukkitInventory.setItem(8, ItemCreator.getItem(Material.ARROW, MessageTranslator.getItemName("page-back")));
    }
}
