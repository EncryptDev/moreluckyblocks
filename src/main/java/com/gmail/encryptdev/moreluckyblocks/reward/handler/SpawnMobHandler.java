package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import com.gmail.encryptdev.moreluckyblocks.mob.MobSettings;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class SpawnMobHandler implements IRewardHandler<EntityType> {

    private Location location;
    private MobSettings mobSettings;
    private EntityType property;

    public SpawnMobHandler(EntityType property) {
        this.property = property;
    }

    public void setMobSettings(MobSettings mobSettings) {
        this.mobSettings = mobSettings;
    }

    @Override
    public void handle() {
        Entity spawnedEntity = location.getWorld().spawnEntity(location, property);
        if (mobSettings != null)
            this.mobSettings.set(spawnedEntity);
    }

    @Override
    public void setLocation(Location paramLocation) {
        this.location = paramLocation;
    }

    @Override
    public Class<EntityType> getProperty() {
        return EntityType.class;
    }

    @Override
    public EntityType getRewardObject() {
        return property;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("entityType", property.toString());
        return map;
    }

    public static SpawnMobHandler deserialize(Map<String, Object> map) {
        return new SpawnMobHandler(EntityType.valueOf((String) map.get("entityType")));
    }


}
