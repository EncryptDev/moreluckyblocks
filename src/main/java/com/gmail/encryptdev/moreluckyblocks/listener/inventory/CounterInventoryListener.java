package com.gmail.encryptdev.moreluckyblocks.listener.inventory;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.MobSettingsInventory;
import com.gmail.encryptdev.moreluckyblocks.mob.MobCacheManager;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by EncryptDev
 */
public class CounterInventoryListener implements Listener {

    private MobCacheManager mobCacheManager;

    public CounterInventoryListener(MobCacheManager mobCacheManager) {
        this.mobCacheManager = mobCacheManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getName().equals("§eCounter | Health")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§eFinish")) {
                double amount = Double.parseDouble(event.getInventory().getItem(13).getItemMeta().getDisplayName().split(":")[1]);
                mobCacheManager.getPlayerCache().get(player).setMaxHealth(amount);
                mobCacheManager.getPlayerCache().get(player).setHealth(amount);
                AbstractInventory.openInventory(player, new MobSettingsInventory());
                return;
            }
            if (!event.getCurrentItem().getItemMeta().getDisplayName().equals("§0")) {
                set(event, "Health");
            }
        } else if (event.getInventory().getName().equals("§eCounter | Speed")
                && !StaticUtil.is1_8()) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§eFinish")) {
                double amount = Double.parseDouble(event.getInventory().getItem(13).getItemMeta().getDisplayName().split(":")[1]);
                mobCacheManager.getPlayerCache().get(player).setSpeed(amount);
                AbstractInventory.openInventory(player, new MobSettingsInventory());
                return;
            }
            if (!event.getCurrentItem().getItemMeta().getDisplayName().equals("§0")) {
                set(event, "Speed");
            }

        } else if (event.getInventory().getName().equals("§eCounter | Attack Damage")
                && !StaticUtil.is1_8()) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§eFinish")) {
                double amount = Double.parseDouble(event.getInventory().getItem(13).getItemMeta().getDisplayName().split(":")[1]);
                mobCacheManager.getPlayerCache().get(player).setAttackSpeed(amount);
                AbstractInventory.openInventory(player, new MobSettingsInventory());
                return;
            }
            if (!event.getCurrentItem().getItemMeta().getDisplayName().equals("§0")) {
                set(event, "Attack Damage");
            }
        }
    }

    private void set(InventoryClickEvent event, String name) {
        String displayName = event.getCurrentItem().getItemMeta().getDisplayName();

        double amount = Double.parseDouble(event.getInventory().getItem(13).getItemMeta().getDisplayName().split(":")[1]);
        switch (displayName) {
            case "§5§l-0.1":
                amount -= 0.1;
                if (amount < 0)
                    amount = 0;
                break;
            case "§5§l-1":
                amount -= 1;
                if (amount < 0)
                    amount = 0;
                break;
            case "§5§l-10":
                amount -= 10;
                if (amount < 0)
                    amount = 0;
                break;
            case "§5§l-25":
                amount -= 25;
                if (amount < 0)
                    amount = 0;
                break;
            case "§5§l+0.1":
                amount += 0.1;
                break;
            case "§5§l+1":
                amount += 1;
                break;
            case "§5§l+10":
                amount += 10;
                break;
            case "§5§l+25":
                amount += 25;
                break;
        }
        ItemStack item = event.getInventory().getItem(13).clone();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e" + name + ": " + amount);
        item.setItemMeta(meta);
        event.getInventory().setItem(13, item);

    }

}
