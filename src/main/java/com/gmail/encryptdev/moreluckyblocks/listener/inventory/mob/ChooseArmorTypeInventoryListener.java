package com.gmail.encryptdev.moreluckyblocks.listener.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.PutInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.MobSettingsInventory;
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
        if (event.getInventory().getName().equals("§eMob Armor")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
            if (displayName.equals("§eHelmet")) {
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.HELMET));
            } else if (displayName.equals("§eChestplate")) {
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.CHESTPLATE));
            } else if (displayName.equals("§eLeggings")) {
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.LEGGINGS));
            } else if (displayName.equals("§eBoots")) {
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.BOOTS));
            } else if (displayName.equals("§eBack")) {
                AbstractInventory.openInventory(player, new MobSettingsInventory());
            }
        }
    }

}
