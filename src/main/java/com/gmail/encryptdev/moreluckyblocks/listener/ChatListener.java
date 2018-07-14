package com.gmail.encryptdev.moreluckyblocks.listener;

import com.gmail.encryptdev.moreluckyblocks.LuckyBlockManager;
import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.MobSettingsInventory;
import com.gmail.encryptdev.moreluckyblocks.mob.MobCacheManager;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.*;
import com.gmail.encryptdev.moreluckyblocks.structure.Structure;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
                    boolean result = HandlerRegistry.getRegistry().registerCustomHandler(new StructureHandler(HandlerRegistry.newHandlerName(), new Structure(structureName)));
                    if (result)
                        player.sendMessage(MessageTranslator.getMessage("added-handler"));
                    else
                        player.sendMessage("§cERROR > Can not add the handler, handler with this properties already exist");
                    break;
                case LuckyBlockManager.CC_MOB_NAME:
                    luckyBlockManager.getChatCommands().remove(player);

                    String mobName = event.getMessage();
                    if (mobName.length() <= 0 || mobName.length() > 36) {
                        player.sendMessage("§cERROR > The mob name must have a length of max 32 character, and a min of 1 character");
                        break;
                    }
                    mobCacheManager.getPlayerCache().get(player).setCustomName(mobName);
                    AbstractInventory.openInventory(player, new MobSettingsInventory());
                    break;
                case LuckyBlockManager.CC_ITEMS_ADD:
                    String message = event.getMessage();

                    if (message.equalsIgnoreCase("add")) {
                        ItemStack itemInHand = StaticUtil.is1_8() ? player.getItemInHand() : player.getInventory().getItemInMainHand();
                        if (luckyBlockManager.getPlayerItems().containsKey(player)) {
                            List<ItemStack> copy = new LinkedList<>(luckyBlockManager.getPlayerItems().remove(player));
                            copy.add(itemInHand);
                            luckyBlockManager.getPlayerItems().put(player, copy);
                            player.sendMessage("§aYou added the item");
                        } else {
                            luckyBlockManager.getPlayerItems().put(player, Arrays.asList(itemInHand));
                            player.sendMessage("§aYou added the item");
                        }
                        return;
                    }
                    if (message.equalsIgnoreCase("finish")) {
                        if (!luckyBlockManager.getPlayerItems().containsKey(player)) {
                            player.sendMessage("§cHandler was not added, because empty item list");
                            luckyBlockManager.getChatCommands().remove(player);
                            return;
                        }
                        List<ItemStack> list = luckyBlockManager.getPlayerItems().remove(player);
                        luckyBlockManager.getHandlerNeedRepeat().put(player, new SpawnItemHandler(HandlerRegistry.newHandlerName(), list));
                        luckyBlockManager.getChatCommands().remove(player);
                        luckyBlockManager.getChatCommands().put(player, LuckyBlockManager.CC_REPEAT);
                        player.sendMessage("§aWrite now the amount of repeat, for the handler (Write 0, for only one)");
                    }
                    break;
                case LuckyBlockManager.CC_HIGH:
                    IRewardHandler rewardHandler = luckyBlockManager.getHandlerNeedRepeat().get(player);
                    int high;
                    try {
                        high = Integer.parseInt(event.getMessage());
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cERROR > It is not a number");
                        return;
                    }
                    if (high < 0) {
                        player.sendMessage("§cERROR > The high can not be lower as 0");
                        return;
                    }
                    ((FallingBlockHandler) rewardHandler).setHigh(high);
                    player.sendMessage("§aYou set the high");
                    player.sendMessage("§aWrite now the amount of repeat, for the handler (Write 0, for only one)");
                    luckyBlockManager.getChatCommands().remove(player);
                    luckyBlockManager.getChatCommands().put(player, LuckyBlockManager.CC_REPEAT);
                    break;
                case LuckyBlockManager.CC_REPEAT:
                    IRewardHandler rewardHandler0 = luckyBlockManager.getHandlerNeedRepeat().get(player);
                    int repeat;
                    try {
                        repeat = Integer.parseInt(event.getMessage());
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cERROR > It is not a number");
                        return;
                    }
                    if (repeat < 0) {
                        player.sendMessage("§cERROR > Repeat can not be lower as 0");
                        return;
                    }
                    rewardHandler0.setRepeat(repeat);
                    boolean result0 = HandlerRegistry.getRegistry().registerCustomHandler(rewardHandler0);
                    if (result0)
                        player.sendMessage(MessageTranslator.getMessage("added-handler"));
                    else
                        player.sendMessage("§cERROR > Can not add the handler, handler with this properties already exist");
                    luckyBlockManager.getHandlerNeedRepeat().remove(player);
                    luckyBlockManager.getChatCommands().remove(player);
                    break;
            }

        }
    }

}
