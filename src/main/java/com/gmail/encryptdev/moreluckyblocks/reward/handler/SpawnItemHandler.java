package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class SpawnItemHandler implements IRewardHandler<ItemStack> {

    private Location location;
    private ItemStack property;

    public SpawnItemHandler(ItemStack property) {
        this.property = property;
    }

    public SpawnItemHandler(Map<String, Object> map) {
        this.property = (ItemStack) map.get("property");
    }

    @Override
    public void handle() {
        this.location.getWorld().dropItem(location, property);
    }

    @Override
    public void setLocation(Location paramLocation) {
        this.location = paramLocation;
    }

    @Override
    public Class<ItemStack> getProperty() {
        return ItemStack.class;
    }

    @Override
    public ItemStack getRewardObject() {
        return property;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("property", property);
        return map;
    }

    public static SpawnItemHandler deserialize(Map<String, Object> map) {
        return new SpawnItemHandler(map);
    }
}
