package com.gmail.encryptdev.moreluckyblocks.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by EncryptDev
 */
public class ChooseAttributeInventory extends AbstractInventory {

    public ChooseAttributeInventory() {
        super("§eMob Attribute", 9);

        bukkitInventory.setItem(0, ItemCreator.getItem(Material.APPLE, "§eHealth"));
        if (StaticUtil.is1_8()) {
            bukkitInventory.setItem(1, ItemCreator.getItem(Material.FEATHER, "§eSpeed", 1,
                    (byte) 0, Arrays.asList("§4§lONLY AVAILABLE IN 1.9+ VERSIONS")));
            bukkitInventory.setItem(2, ItemCreator.getItem(Material.DIAMOND_SWORD, "§eAttack Damage", 1,
                    (byte) 0, Arrays.asList("§4§lONLY AVAILABLE IN 1.9+ VERSIONS")));
        } else {
            bukkitInventory.setItem(1, ItemCreator.getItem(Material.FEATHER, "§eSpeed"));
            bukkitInventory.setItem(2, ItemCreator.getItem(Material.DIAMOND_SWORD, "§eAttack Damage"));
        }
        bukkitInventory.setItem(8, ItemCreator.getItem(Material.ARROW, "§eBack"));

    }
}
