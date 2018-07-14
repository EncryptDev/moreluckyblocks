package com.gmail.encryptdev.moreluckyblocks.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by EncryptDev
 */
public class ChooseAttributeInventory extends AbstractInventory {

    public ChooseAttributeInventory() {
        super(MessageTranslator.getInventoryName("mob-attribute"), 9);

        bukkitInventory.setItem(0, ItemCreator.getItem(Material.APPLE, MessageTranslator.getItemName("mob-attribute-inventory-health")));
        if(StaticUtil.is1_8()) {
            bukkitInventory.setItem(1, ItemCreator.getItem(Material.FEATHER, MessageTranslator.getItemName("mob-attribute-inventory-speed"), 1,
                    (byte) 0, Arrays.asList("§4§lONLY AVAILABLE IN 1.9+ VERSIONS")));
            bukkitInventory.setItem(2, ItemCreator.getItem(Material.DIAMOND_SWORD, MessageTranslator.getItemName("mob-attribute-inventory-attack-speed"), 1,
                    (byte) 0, Arrays.asList("§4§lONLY AVAILABLE IN 1.9+ VERSIONS")));
        } else {
            bukkitInventory.setItem(1, ItemCreator.getItem(Material.FEATHER, MessageTranslator.getItemName("mob-attribute-inventory-speed")));
            bukkitInventory.setItem(2, ItemCreator.getItem(Material.DIAMOND_SWORD, MessageTranslator.getItemName("mob-attribute-inventory-attack-speed")));
        }
        bukkitInventory.setItem(8, ItemCreator.getItem(Material.ARROW, MessageTranslator.getItemName("page-back")));

    }
}
