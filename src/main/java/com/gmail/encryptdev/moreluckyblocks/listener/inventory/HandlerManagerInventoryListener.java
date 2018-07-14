package com.gmail.encryptdev.moreluckyblocks.listener.inventory;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.HandlerManagerInventory;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.HandlerRegistry;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by EncryptDev
 */
public class HandlerManagerInventoryListener implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getName().startsWith("§eHandler Manager")) {
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
            String[] splitted = event.getInventory().getName().split("#");
            int page = Integer.parseInt(splitted[splitted.length - 1]);
            if (event.getInventory().getName().equalsIgnoreCase("§eHandler Manager #" + page)) {
                event.setCancelled(true);
                int maxPages = StaticUtil.calculateMaxPages(HandlerRegistry.getRegistry().getCustomHandler().size());
                if (displayName.equals("§eNext")) {
                    if (!(page >= maxPages))
                        AbstractInventory.openInventory(player, new HandlerManagerInventory(page + 1));
                    return;
                } else if (displayName.equals("§eBack")) {
                    if (!(page <= 1))
                        AbstractInventory.openInventory(player, new HandlerManagerInventory(page - 1));
                    return;
                }

                if (displayName.equals("§0"))
                    return;

                String stripped = ChatColor.stripColor(displayName);
                String handlerName = stripped.toLowerCase().replace(" ", "_");
                boolean result = HandlerRegistry.getRegistry().unregisterCustomHandler(handlerName);
                player.closeInventory();
                if (result)
                    player.sendMessage("§aYou removed the handler successfully");
                else
                    player.sendMessage("§aHandler can not be removed. Handler doesn't exist in the cache list. Please contact me");

            }
        }
    }

}
