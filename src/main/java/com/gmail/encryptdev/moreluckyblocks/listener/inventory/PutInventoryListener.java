package com.gmail.encryptdev.moreluckyblocks.listener.inventory;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.PutInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.ChooseArmorTypeInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.ChooseWeaponInventory;
import com.gmail.encryptdev.moreluckyblocks.mob.MobCacheManager;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class PutInventoryListener implements Listener {

    private MobCacheManager mobCacheManager;

    public PutInventoryListener(MobCacheManager mobCacheManager) {
        this.mobCacheManager = mobCacheManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        if (event.getInventory().getName().equals("§ePut Inventory | " + StaticUtil.enumToNormal(PutInventory.PutType.HELMET))) {
            if (event.getCurrentItem() == null)
                return;
            setItemInCache(event, 0);
        } else if (event.getInventory().getName().equals("§ePut Inventory | " + StaticUtil.enumToNormal(PutInventory.PutType.CHESTPLATE))) {
            if (event.getCurrentItem() == null)
                return;
            setItemInCache(event, 1);
        } else if (event.getInventory().getName().equals("§ePut Inventory | " + StaticUtil.enumToNormal(PutInventory.PutType.LEGGINGS))) {
            if (event.getCurrentItem() == null)
                return;
            setItemInCache(event, 2);
        } else if (event.getInventory().getName().equals("§ePut Inventory | " + StaticUtil.enumToNormal(PutInventory.PutType.BOOTS))) {
            if (event.getCurrentItem() == null)
                return;
            setItemInCache(event, 3);
        } else if (event.getInventory().getName().equals("§ePut Inventory | " + StaticUtil.enumToNormal(PutInventory.PutType.MAIN_HAND))) {
            if (event.getCurrentItem() == null)
                return;
            setItemInCache(event, 4);
        } else if (event.getInventory().getName().equals("§ePut Inventory | " + StaticUtil.enumToNormal(PutInventory.PutType.OFF_HAND))) {
            if (event.getCurrentItem() == null)
                return;
            setItemInCache(event, 5);

        }
    }

    private void setItemInCache(InventoryClickEvent event, int slot) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem().hasItemMeta()) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§0")) {
                event.setCancelled(true);
                return;
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§eFinish")) {
                event.setCancelled(true);
                ItemStack item = event.getInventory().getItem(0);
                switch (slot) {
                    case 0:
                        mobCacheManager.getPlayerCache().get(player).setHelmet(item);
                        AbstractInventory.openInventory(player, new ChooseArmorTypeInventory());
                        break;
                    case 1:
                        mobCacheManager.getPlayerCache().get(player).setChestplate(item);
                        AbstractInventory.openInventory(player, new ChooseArmorTypeInventory());
                        break;
                    case 2:
                        mobCacheManager.getPlayerCache().get(player).setLeggings(item);
                        AbstractInventory.openInventory(player, new ChooseArmorTypeInventory());
                        break;
                    case 3:
                        mobCacheManager.getPlayerCache().get(player).setBoots(item);
                        AbstractInventory.openInventory(player, new ChooseArmorTypeInventory());
                        break;
                    case 4:
                        mobCacheManager.getPlayerCache().get(player).setMainHand(item);
                        AbstractInventory.openInventory(player, new ChooseWeaponInventory());
                        break;
                    case 5:
                        mobCacheManager.getPlayerCache().get(player).setOffHand(item);
                        AbstractInventory.openInventory(player, new ChooseWeaponInventory());
                        break;
                }
            }
        }
    }


}
