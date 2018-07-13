package com.gmail.encryptdev.moreluckyblocks.listener;

import com.gmail.encryptdev.moreluckyblocks.LuckyBlockManager;
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

    public ChatListener(LuckyBlockManager luckyBlockManager) {
        this.luckyBlockManager = luckyBlockManager;
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
            }

        }
    }

}
