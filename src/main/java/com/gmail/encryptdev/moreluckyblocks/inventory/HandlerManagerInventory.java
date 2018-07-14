package com.gmail.encryptdev.moreluckyblocks.inventory;

import com.gmail.encryptdev.moreluckyblocks.mob.MobSettings;
import com.gmail.encryptdev.moreluckyblocks.reward.fallingblock.FallingBlockType;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.HandlerRegistry;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.IRewardHandler;
import com.gmail.encryptdev.moreluckyblocks.structure.Structure;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by EncryptDev
 */
public class HandlerManagerInventory extends AbstractInventory {

    public HandlerManagerInventory(int page) {
        super("§eHandler Manager #" + page, 54);

        List<List<ItemStack>> subLists = new LinkedList<>();
        List<ItemStack> items = new LinkedList<>();

        for (IRewardHandler iRewardHandler : HandlerRegistry.getRegistry().getCustomHandler()) {
            String handlerNameRaw = iRewardHandler.getHandlerName();
            String handlerName = handlerNameRaw.replace(handlerNameRaw.charAt(0), Character.toUpperCase(handlerNameRaw.charAt(0)))
                    .replace("_", " ");

            List<String> lore = new ArrayList<>();
            lore.add("§eClick to remove the handler");
            lore.add("§eReward Object(s):");
            Object rewardObject = iRewardHandler.getRewardObject();
            if (rewardObject instanceof List) {
                List<ItemStack> itemStacks = (List<ItemStack>) rewardObject;
                for (ItemStack itemStack : itemStacks)
                    lore.add("§c" + (itemStack.hasItemMeta() ? itemStack.getItemMeta().getDisplayName() : itemStack.getType().toString()));
            } else if (rewardObject instanceof Structure) {
                lore.add("§eStructure Name: §c" + ((Structure) rewardObject).getStructureName());
            } else if (rewardObject instanceof FallingBlockType) {
                lore.add("§eFalling Block Type: §c" + ((FallingBlockType) rewardObject).getMaterial().toString());
            } else if (rewardObject instanceof MobSettings) {
                MobSettings mobSettings = (MobSettings) rewardObject;
                lore.add("§eMobName: §c" + mobSettings.getCustomName());
            } else {
                lore.add("-/-");
            }
            items.add(ItemCreator.getItem(Material.PAPER, "§5" + handlerName,
                    lore));
        }

        fill();

        if (!items.isEmpty()) {
            int maxPages = StaticUtil.calculateMaxPages(items.size());
            int n = 0;

            if (maxPages > 1) {
                for (int i = 0; i < maxPages; i++) {
                    subLists.add(items.subList(n, (n + 45 > items.size() ? items.size() : n + 45)));
                    n += 45;
                }
            } else {
                subLists.add(items.subList(0, items.size()));
            }

            for (int i = 0; i < subLists.get(page - 1).size(); i++) {
                bukkitInventory.setItem(i, subLists.get(page - 1).get(i));
            }
        }

        bukkitInventory.setItem(45, ItemCreator.getItem(Material.ARROW, "§eBack"));
        bukkitInventory.setItem(53, ItemCreator.getItem(Material.ARROW, "§eNext"));

    }
}
