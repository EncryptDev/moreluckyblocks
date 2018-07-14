package com.gmail.encryptdev.moreluckyblocks.listener;

import com.gmail.encryptdev.moreluckyblocks.LuckyBlockManager;
import com.gmail.encryptdev.moreluckyblocks.MoreLuckyBlocks;
import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.MobSettingsInventory;
import com.gmail.encryptdev.moreluckyblocks.mob.MobCacheManager;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.HandlerRegistry;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.StructureHandler;
import com.gmail.encryptdev.moreluckyblocks.structure.Structure;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by EncryptDev
 */
public class ChatListener implements Listener {

    private LuckyBlockManager luckyBlockManager;
    private MobCacheManager mobCacheManager;

    public ChatListener(LuckyBlockManager luckyBlockManager, MobCacheManager mobCacheManager) {
        this.luckyBlockManager = luckyBlockManager;
        this.mobCacheManager = mobCacheManager;
    }

    @EventHandler
    public void on(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (luckyBlockManager.getChatCommands().containsKey(player)) {
            event.setCancelled(true);
            String chatCommand = luckyBlockManager.getChatCommands().get(player);

            switch (chatCommand) {
                case LuckyBlockManager.CC_STRUCTURE_NAME:
                    luckyBlockManager.getChatCommands().remove(player);

                    String structureName = event.getMessage();
                    if (structureName.split(" ").length != 1) {
                        player.sendMessage("§cERROR > The structure name must have only one word");
                        break;
                    }
                    boolean result = HandlerRegistry.getRegistry().registerCustomHandler(new StructureHandler(new Structure(structureName)));
                    if (result)
                        player.sendMessage(MessageTranslator.getMessage("added-handler"));
                    else
                        player.sendMessage("§cERROR > The structure handler already exist");
                    break;
                case LuckyBlockManager.CC_MOB_NAME:
                    luckyBlockManager.getChatCommands().remove(player);

                    String mobName = event.getMessage();
                    if(mobName.length() <= 0 || mobName.length() > 36) {
                        player.sendMessage("§cERROR > The mob name must have a length of max 32 character, and a min of 1 character");
                        break;
                    }
                    mobCacheManager.getPlayerCache().get(player).setCustomName(mobName);
                    AbstractInventory.openInventory(player, new MobSettingsInventory());
                    break;
            }

        }
    }

}
