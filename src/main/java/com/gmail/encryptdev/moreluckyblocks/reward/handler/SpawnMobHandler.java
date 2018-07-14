package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import com.gmail.encryptdev.moreluckyblocks.mob.MobSettings;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class SpawnMobHandler implements IRewardHandler<MobSettings> {

    private Location location;
    private MobSettings property;

    public SpawnMobHandler(MobSettings mobSettings) {
        this.property = mobSettings;
    }

    public SpawnMobHandler(Map<String, Object> map) {
        this.property = (MobSettings) map.get("mobSettings");
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
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("mobSettings", property);
        return map;
    }

    public static SpawnMobHandler deserialize(Map<String, Object> map) {
        return new SpawnMobHandler(map);
    }


}
