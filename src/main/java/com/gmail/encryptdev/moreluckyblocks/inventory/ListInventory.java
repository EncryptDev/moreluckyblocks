package com.gmail.encryptdev.moreluckyblocks.inventory;

import com.gmail.encryptdev.moreluckyblocks.reward.fallingblock.FallingBlockType;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by EncryptDev
 */
public class ListInventory extends AbstractInventory {

    public ListInventory(String name, Enum[] enumArray, int page) {
        super(name + " #" + page, 54);
        List<List<ItemStack>> subLists = new ArrayList<>();
        fill();

        List<ItemStack> items = new LinkedList<>();

        if (enumArray[0].getClass().equals(Material.class)) {
            Material[] materials = (Material[]) enumArray;
            for (Material m : materials)
                items.add(ItemCreator.getItem(m, "§e" + StaticUtil.enumToNormal(m), 1));
        } else if (enumArray[0].getClass().equals(EntityType.class)) {
            EntityType[] entityTypes = (EntityType[]) enumArray;
            for (EntityType et : entityTypes)
                items.add(ItemCreator.getItem(Material.MONSTER_EGG, "§e" + StaticUtil.enumToNormal(et), 1, (byte) et.getTypeId()));
        } else if(enumArray[0].getClass().equals(FallingBlockType.class)) {
            FallingBlockType[] fallingBlockTypes = (FallingBlockType[]) enumArray;
            for(FallingBlockType fbt : fallingBlockTypes)
                items.add(ItemCreator.getItem(fbt.getMaterial(), "§e" + StaticUtil.enumToNormal(fbt)));
        }

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
