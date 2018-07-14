package com.gmail.encryptdev.moreluckyblocks.inventory;

import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by EncryptDev
 */
public class MobSettingsInventory extends AbstractInventory {

    public MobSettingsInventory() {
        super("§eMob Settings", 54);

        fill();
        bukkitInventory.setItem(11, ItemCreator.getItem(Material.DIAMOND_HELMET, "§eHelmet"));
        bukkitInventory.setItem(12, ItemCreator.getItem(Material.DIAMOND_CHESTPLATE, "§eChestplate"));
        bukkitInventory.setItem(13, ItemCreator.getItem(Material.DIAMOND_LEGGINGS, "§eLeggings"));
        bukkitInventory.setItem(14, ItemCreator.getItem(Material.DIAMOND_BOOTS, "§eBoots"));
        bukkitInventory.setItem(15, ItemCreator.getItem(Material.NAME_TAG, "§eMob Name"));
        bukkitInventory.setItem(20, ItemCreator.getItem(Material.DIAMOND_SWORD, "§eMain Hand"));
        bukkitInventory.setItem(21, ItemCreator.getItem(Material.DIAMOND_HOE, "§eOff Hand",
                Arrays.asList("§4§lONLY AVAILABLE IN 1.9+ OR HIGHER VERSIONS")));
        bukkitInventory.setItem(22, ItemCreator.getItem(Material.APPLE, "§eHealth"));
        bukkitInventory.setItem(23, ItemCreator.getItem(Material.FEATHER, "§eSpeed",
                Arrays.asList("§4§lONLY AVAILABLE IN 1.9+ OR HIGHER VERSIONS")));
        bukkitInventory.setItem(24, ItemCreator.getItem(Material.GOLD_SWORD, "§eAttack Damage",
                Arrays.asList("§4§lONLY AVAILABLE IN 1.9+ OR HIGHER VERSIONS")));

        bukkitInventory.setItem(38, ItemCreator.getItem(Material.POTION, "§ePotionEffect Regeneration", Arrays.asList("§eStatus: §4§lOFF")));
        bukkitInventory.setItem(39, ItemCreator.getItem(Material.POTION, "§ePotionEffect Invisibility", Arrays.asList("§eStatus: §4§lOFF")));
        bukkitInventory.setItem(40, ItemCreator.getItem(Material.POTION, "§ePotionEffect Strength", Arrays.asList("§eStatus: §4§lOFF")));
        bukkitInventory.setItem(41, ItemCreator.getItem(Material.POTION, "§ePotionEffect Speed", Arrays.asList("§eStatus: §4§lOFF")));


        bukkitInventory.setItem(53, ItemCreator.getItem(Material.DIAMOND, "§eFinish"));
    }
}
