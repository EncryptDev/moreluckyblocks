package com.gmail.encryptdev.moreluckyblocks.listener.inventory;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.AddNewRewardInventory;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by EncryptDev
 */
public class LuckyBlockInventoryListener implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getName().equals(MessageTranslator.getInventoryName("luckyblock-main"))) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
            if(displayName.equals(MessageTranslator.getItemName("add-new-reward"))) {
                AbstractInventory.openInventory(player, new AddNewRewardInventory());
            } else if(displayName.equals(MessageTranslator.getItemName("structure-manager"))) {

            }

        }

    }

}
