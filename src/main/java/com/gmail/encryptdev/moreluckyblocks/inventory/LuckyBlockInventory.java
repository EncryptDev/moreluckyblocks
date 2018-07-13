package com.gmail.encryptdev.moreluckyblocks.inventory;

import com.gmail.encryptdev.moreluckyblocks.MoreLuckyBlocks;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by EncryptDev
 */
public class LuckyBlockInventory extends AbstractInventory {

    public LuckyBlockInventory() {
        super(MessageTranslator.getInventoryName("luckyblock-main"), 9);
        this.fill();
        this.bukkitInventory.setItem(0, ItemCreator.getItem(Material.REDSTONE, MessageTranslator.getItemName("add-new-reward")));
        this.bukkitInventory.setItem(1, ItemCreator.getItem(Material.WOOD_AXE, MessageTranslator.getItemName("structure-manager")));
        this.bukkitInventory.setItem(8, ItemCreator.getItem(Material.PAPER, "§ePLUGIN-INFO", Arrays.asList("§eDeveloper: §cEncryptDev",
                "§eVersion: §c" + MoreLuckyBlocks.getInstance().getDescription().getVersion(),
                "§eHelp ? Join my discord [Link on the spigot page]")));
    }
}
