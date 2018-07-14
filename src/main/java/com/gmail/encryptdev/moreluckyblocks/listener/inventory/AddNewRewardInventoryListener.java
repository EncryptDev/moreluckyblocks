package com.gmail.encryptdev.moreluckyblocks.listener.inventory;

import com.gmail.encryptdev.moreluckyblocks.LuckyBlockManager;
import com.gmail.encryptdev.moreluckyblocks.MoreLuckyBlocks;
import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.HandlerManagerInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.ListInventory;
import com.gmail.encryptdev.moreluckyblocks.reward.fallingblock.FallingBlockType;
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
        if (event.getInventory().getName().equals("§eAdd a new reward")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            ItemStack itemStack = event.getCurrentItem();
            String displayName = itemStack.getItemMeta().getDisplayName();
            if (displayName.equals("§eAdd a new structure handler")) {
                player.closeInventory();
                if(MoreLuckyBlocks.getInstance().isWorldEditLoaded()) {
                    if (!luckyBlockManager.getChatCommands().containsKey(player)) {
                        luckyBlockManager.getChatCommands().put(player, LuckyBlockManager.CC_STRUCTURE_NAME);
                        player.sendMessage(MessageTranslator.getMessage("write-structure-name"));
                    }
                } else {
                    player.sendMessage("§cYou can not use the structure rewards, without worldedit");
                }
            } else if (displayName.equals("§eAdd a new mob handler")) {
                AbstractInventory.openInventory(player, new ListInventory("§eEntity List", EntityTypeUtil.getEntityTypes(), 1));
            } else if (displayName.equals("§eAdd a new item handler")) {
                player.closeInventory();
                if (!luckyBlockManager.getChatCommands().containsKey(player)) {
                    luckyBlockManager.getChatCommands().put(player, LuckyBlockManager.CC_ITEMS_ADD);
                    player.sendMessage(MessageTranslator.getMessage("add-items"));
                }
            } else if (displayName.equalsIgnoreCase("§eAdd a new falling block handler")) {
                AbstractInventory.openInventory(player, new ListInventory("§eFalling Blocks", FallingBlockType.values(), 1));
            } else if (displayName.equalsIgnoreCase("§eHandler Manager")) {
                AbstractInventory.openInventory(player, new HandlerManagerInventory(1));
            }
        }
    }

}
