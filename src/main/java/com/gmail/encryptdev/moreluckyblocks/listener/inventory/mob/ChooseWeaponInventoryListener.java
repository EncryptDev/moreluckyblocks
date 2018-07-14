package com.gmail.encryptdev.moreluckyblocks.listener.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.PutInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.MobSettingsInventory;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by EncryptDev
 */
public class ChooseWeaponInventoryListener implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getName().equals("§eMob Weapons")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§eMain Hand")) {
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.MAIN_HAND));
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§eOff Hand") && !StaticUtil.is1_8()) {
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.OFF_HAND));
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§eBack")) {
                AbstractInventory.openInventory(player, new MobSettingsInventory());
            }
        }

    }

}
