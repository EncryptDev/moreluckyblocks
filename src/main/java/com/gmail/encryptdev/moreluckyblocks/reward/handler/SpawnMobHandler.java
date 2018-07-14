package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import com.gmail.encryptdev.moreluckyblocks.mob.MobSettings;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class SpawnMobHandler implements IRewardHandler<MobSettings> {

    private String handlerName;
    private Location location;
    private MobSettings property;
    private int repeat;

    public SpawnMobHandler(String handlerName, MobSettings mobSettings) {
        this.handlerName = handlerName;
        this.property = mobSettings;
        this.repeat = 0;
    }

    public SpawnMobHandler(Map<String, Object> map) {
        this.handlerName = (String) map.get("handlerName");
        this.property = (MobSettings) map.get("mobSettings");
        this.repeat = (int) map.get("repeat");
    }

    @Override
    public void handle() {
        property.build(location);
    }

    @Override
    public void setLocation(Location paramLocation) {
        this.location = paramLocation;
    }

    @Override
    public MobSettings getRewardObject() {
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
        map.put("mobSettings", property);
        map.put("repeat", repeat);
        return map;
    }

    public static SpawnMobHandler deserialize(Map<String, Object> map) {
        return new SpawnMobHandler(map);
    }


}
