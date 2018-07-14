package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class SpawnItemHandler implements IRewardHandler<List<ItemStack>> {

    private String handlerName;
    private Location location;
    private List<ItemStack> property;
    private int repeat;

    public SpawnItemHandler(String handlerName, List<ItemStack> property) {
        this.handlerName = handlerName;
        this.property = property;
        this.repeat = 0;
    }

    public SpawnItemHandler(Map<String, Object> map) {
        this.handlerName = (String) map.get("handlerName");
        this.property = (List<ItemStack>) map.get("property");
        this.repeat = (int) map.get("repeat");
    }

    @Override
    public void handle() {
        for (ItemStack item : property)
            this.location.getWorld().dropItem(location, item);
    }

    @Override
    public void setLocation(Location paramLocation) {
        this.location = paramLocation;
    }

    @Override
    public List<ItemStack> getRewardObject() {
        return property;
    }

    @Override
    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    @Override
    public int getRepeat() {
        return repeat;
    }

    @Override
    public String getHandlerName() {
        return handlerName;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("handlerName", handlerName);
        map.put("property", property);
        map.put("repeat", repeat);
        return map;
    }

    public static SpawnItemHandler deserialize(Map<String, Object> map) {
        return new SpawnItemHandler(map);
    }
}
