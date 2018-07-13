package com.gmail.encryptdev.moreluckyblocks.listener.inventory;

import com.gmail.encryptdev.moreluckyblocks.LuckyBlockManager;
import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.ListInventory;
import com.gmail.encryptdev.moreluckyblocks.util.EntityTypeUtil;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class AddNewRewardInventoryListener implements Listener {

    private LuckyBlockManager luckyBlockManager;

    public AddNewRewardInventoryListener(LuckyBlockManager luckyBlockManager) {
        this.luckyBlockManager = luckyBlockManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getName().equals(MessageTranslator.getInventoryName("add-new-reward"))) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            ItemStack itemStack = event.getCurrentItem();
            String displayName = itemStack.getItemMeta().getDisplayName();
            if (displayName.equals(MessageTranslator.getItemName("add-structure"))) {
                player.closeInventory();
                luckyBlockManager.getChatCommands().put(player, LuckyBlockManager.CC_STRUCTURE_NAME);
                player.sendMessage(MessageTranslator.getMessage("write-structure-name"));
            } else if (displayName.equals(MessageTranslator.getItemName("add-new-mob"))) {
                AbstractInventory.openInventory(player, new ListInventory(MessageTranslator.getInventoryName("mob-list"), EntityTypeUtil.getEntityTypes(), 1));
            } else if (displayName.equals(MessageTranslator.getItemName("add-new-item"))) {

            } else if (displayName.equals(MessageTranslator.getItemName("add-command"))) {

            }
        }
    }

}
