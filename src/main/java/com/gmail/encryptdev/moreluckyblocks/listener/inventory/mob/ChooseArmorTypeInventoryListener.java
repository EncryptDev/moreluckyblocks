package com.gmail.encryptdev.moreluckyblocks.listener.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.PutInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.MobSettingsInventory;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by EncryptDev
 */
public class ChooseArmorTypeInventoryListener implements Listener {


    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getName().equals(MessageTranslator.getInventoryName("mob-armor"))) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
            if (displayName.equals(MessageTranslator.getItemName("mob-armor-inventory-helmet"))) {
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.HELMET));
            } else if (displayName.equals(MessageTranslator.getItemName("mob-armor-inventory-chestplate"))) {
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.CHESTPLATE));
            } else if (displayName.equals(MessageTranslator.getItemName("mob-armor-inventory-leggings"))) {
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.LEGGINGS));
            } else if (displayName.equals(MessageTranslator.getItemName("mob-armor-inventory-boots"))) {
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.BOOTS));
            } else if (displayName.equals(MessageTranslator.getItemName("page-back"))) {
                AbstractInventory.openInventory(player, new MobSettingsInventory());
            }
        }
    }

}
