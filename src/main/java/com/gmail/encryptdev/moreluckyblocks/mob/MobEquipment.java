package com.gmail.encryptdev.moreluckyblocks.mob;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class MobEquipment implements ConfigurationSerializable {

    private ItemStack[] armor;
    private ItemStack[] handItems;

    public MobEquipment(ItemStack[] armor, ItemStack[] handItems) {
        this.armor = armor;
        this.handItems = handItems;
    }

    public MobEquipment(Map<String, Object> map) {
        this.armor = ((List<ItemStack>) map.get("armor")).toArray(new ItemStack[0]);
        this.handItems = ((List<ItemStack>) map.get("handItems")).toArray(new ItemStack[0]);
    }

    public MobEquipment() {
        this(new ItemStack[4], new ItemStack[2]);
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public ItemStack[] getHandItems() {
        return handItems;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("armor", armor);
        map.put("handItems", handItems);
        return map;
    }

    public static MobEquipment deserialize(Map<String, Object> map) {
        return new MobEquipment(map);
    }

}
