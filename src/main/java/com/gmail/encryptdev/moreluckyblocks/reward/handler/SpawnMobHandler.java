package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
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
        return null;
    }

    public static class MobSettings implements ConfigurationSerializable {

        private String customName;
        private double health;
        private double maxHealth;
        private MobEquipment mobEquipment;

        public MobSettings(String customName, double health, double maxHealth, MobEquipment mobEquipment) {
            this.customName = customName;
            this.health = health;
            this.maxHealth = maxHealth;
            this.mobEquipment = mobEquipment;
        }

        public MobSettings(Map<String, Object> map) {
            this.customName = (String) map.get("customName");
            this.health = (double) map.get("health");
            this.maxHealth = (double) map.get("maxHealth");
            this.mobEquipment = (MobEquipment) map.get("mobEquipment");
        }

        public void set(Entity entity) {
            if (!customName.equalsIgnoreCase(""))
                entity.setCustomName(customName);
            entity.setCustomNameVisible(true);
            if (!(entity instanceof LivingEntity))
                return;
            LivingEntity livingEntity = (LivingEntity) entity;
            if (StaticUtil.is1_8()) {
                livingEntity.setMaxHealth(maxHealth);
                livingEntity.setHealth(health);
            } else {
                livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
                livingEntity.setHealth(maxHealth);
            }
            if (!StaticUtil.isEmpty(mobEquipment.getArmor())) {
                livingEntity.getEquipment().setArmorContents(mobEquipment.getArmor());
            }
            if (!StaticUtil.isEmpty(mobEquipment.getHandItems())) {
                if (StaticUtil.is1_8()) {
                    livingEntity.getEquipment().setItemInHand(mobEquipment.getHandItems()[0]);
                } else {
                    livingEntity.getEquipment().setItemInMainHand(mobEquipment.getHandItems()[0]);
                    livingEntity.getEquipment().setItemInOffHand(mobEquipment.getHandItems()[1]);
                }
            }
        }

        @Override
        public Map<String, Object> serialize() {
            Map<String, Object> map = new HashMap<>();
            map.put("customName", customName);
            map.put("health", health);
            map.put("maxHealth", maxHealth);
            map.put("mobEquipment", mobEquipment);
            return map;
        }

        public static MobSettings deserialize(Map<String, Object> map) {
            return new MobSettings(map);
        }
    }

    public static class MobEquipment implements ConfigurationSerializable {

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

}
