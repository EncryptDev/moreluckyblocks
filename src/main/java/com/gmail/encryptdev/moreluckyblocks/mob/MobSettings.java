package com.gmail.encryptdev.moreluckyblocks.mob;

import com.gmail.encryptdev.moreluckyblocks.reward.handler.SpawnMobHandler;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class MobSettings implements ConfigurationSerializable {

    private String customName;
    private double health;
    private double maxHealth;
    private double speed;
    private double attackSpeed;
    private MobEquipment mobEquipment;
    private List<PotionEffect> potionEffects;

    public MobSettings(String customName, double health, double speed, double attackSpeed, double maxHealth, MobEquipment mobEquipment, List<PotionEffect> potionEffects) {
        this.customName = customName;
        this.health = health;
        this.speed = speed;
        this.maxHealth = maxHealth;
        this.attackSpeed = attackSpeed;
        this.mobEquipment = mobEquipment;
        this.potionEffects = potionEffects;
    }

    public MobSettings(Map<String, Object> map) {
        this.customName = (String) map.get("customName");
        this.health = (double) map.get("health");
        this.speed = (double) map.get("speed");
        this.attackSpeed = (double) map.get("attackSpeed");
        this.maxHealth = (double) map.get("maxHealth");
        this.mobEquipment = (MobEquipment) map.get("mobEquipment");
        this.potionEffects = (List<PotionEffect>) map.get("potionEffects");
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
            if(speed != -1)
                livingEntity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
            if(attackSpeed != -1)
                livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attackSpeed);
        }
        if(potionEffects != null && !potionEffects.isEmpty())
            livingEntity.addPotionEffects(potionEffects);
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
        map.put("speed", speed);
        map.put("maxHealth", maxHealth);
        map.put("mobEquipment", mobEquipment);
        map.put("potionEffects", potionEffects);
        return map;
    }

    public static MobSettings deserialize(Map<String, Object> map) {
        return new MobSettings(map);
    }

}
