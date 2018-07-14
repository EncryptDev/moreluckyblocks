package com.gmail.encryptdev.moreluckyblocks.inventory;

import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.Material;

/**
 * Created by EncryptDev
 */
public class PutInventory extends AbstractInventory {

    public PutInventory(PutType putType) {
        super("§ePut Inventory | " + StaticUtil.enumToNormal(putType), 9);

        fill();
        bukkitInventory.setItem(0, null);
        bukkitInventory.setItem(8, ItemCreator.getItem(Material.EMERALD, "§eFinish"));

    }

    public enum PutType {

        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS,
        MAIN_HAND,
        OFF_HAND;

    }
}
