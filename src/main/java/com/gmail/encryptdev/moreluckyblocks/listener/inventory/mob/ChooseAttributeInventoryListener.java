package com.gmail.encryptdev.moreluckyblocks.listener.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.CounterInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.MobSettingsInventory;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by EncryptDev
 */
public class ChooseAttributeInventoryListener implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getName().equals(MessageTranslator.getInventoryName("mob-attribute"))) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
            if(displayName.equals(MessageTranslator.getItemName("mob-attribute-inventory-health"))) {
                AbstractInventory.openInventory(player, new CounterInventory("Health"));
            } else if(displayName.equals(MessageTranslator.getItemName("mob-attribute-inventory-speed")) && !StaticUtil.is1_8()) {
                AbstractInventory.openInventory(player, new CounterInventory("Speed"));
            } else if(displayName.equals(MessageTranslator.getItemName("mob-attribute-inventory-attack-speed")) && !StaticUtil.is1_8()) {
                AbstractInventory.openInventory(player, new CounterInventory("Attack Speed"));
            } else if(displayName.equals(MessageTranslator.getItemName("page-back"))) {
                AbstractInventory.openInventory(player, new MobSettingsInventory());
            }
        }

    }

}
