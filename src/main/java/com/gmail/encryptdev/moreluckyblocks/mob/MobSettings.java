package com.gmail.encryptdev.moreluckyblocks.mob;

import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class MobSettings implements ConfigurationSerializable {

    private EntityType entityType;
    private String customName;
    private double health;
    private double maxHealth;
    private double speed;
    private double attackSpeed;
    private ItemStack[] armor;
    private ItemStack[] handItems;
    private List<PotionEffect> potionEffects;

    public MobSettings(EntityType entityType) {
        this.entityType = entityType;
        this.customName = "";
        this.potionEffects = new LinkedList<>();
        this.armor = new ItemStack[4];
        this.handItems = new ItemStack[2];
    }

    public MobSettings(Map<String, Object> map) {
        this.entityType = EntityType.valueOf((String) map.get("entityType"));
        this.customName = (String) map.get("customName");
        this.health = (double) map.get("health");
        this.speed = (double) map.get("speed");
        this.attackSpeed = (double) map.get("attackSpeed");
        this.maxHealth = (double) map.get("maxHealth");
        this.armor = ((List<ItemStack>) map.get("armor")).toArray(new ItemStack[0]);
        this.handItems = ((List<ItemStack>) map.get("handItems")).toArray(new ItemStack[0]);
        this.potionEffects = (List<PotionEffect>) map.get("potionEffects");
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setHelmet(ItemStack helmet) {
        this.armor[0] = helmet;
    }

    public void setChestplate(ItemStack chestplate) {
        this.armor[1] = chestplate;
    }

    public void setLeggings(ItemStack leggings) {
        this.armor[2] = leggings;
    }

    public void setBoots(ItemStack boots) {
        this.armor[3] = boots;
    }

    public void setMainHand(ItemStack mainHand) {
        this.handItems[0] = mainHand;
    }

    public void setOffHand(ItemStack offHand) {
        this.handItems[1] = offHand;
    }

    public String getCustomName() {
        return customName;
    }

    public void addPotionEffect(PotionEffect potionEffect) {
        if (this.potionEffects.contains(potionEffect))
            return;
        this.potionEffects.add(potionEffect);
    }

    public void removePotionEffect(PotionEffectType type) {
        PotionEffect toRemove = null;
        for (PotionEffect potionEffect : potionEffects)
            if (potionEffect.getType() == type) {
                toRemove = potionEffect;
                break;
            }
        if (toRemove != null)
            potionEffects.remove(toRemove);
    }

    public MobSettings build(Location location) {
        Entity entity = location.getWorld().spawnEntity(location, entityType);
        if (!customName.equalsIgnoreCase(""))
            entity.setCustomName(customName);
        entity.setCustomNameVisible(true);
        LivingEntity livingEntity = (LivingEntity) entity;
        if (StaticUtil.is1_8()) {
            livingEntity.setMaxHealth(maxHealth);
            livingEntity.setHealth(health);
        } else {
            if(maxHealth == 0 || health == 0) {
                livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                livingEntity.setHealth(20);
            } else {
                livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
                livingEntity.setHealth(maxHealth);
            }
            if (speed != -1)
                livingEntity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
            if (attackSpeed != -1 && livingEntity instanceof Monster)
                livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attackSpeed);
        }
        if (potionEffects != null && !potionEffects.isEmpty())
            livingEntity.addPotionEffects(potionEffects);

        if (armor[0] != null)
            livingEntity.getEquipment().setHelmet(armor[0]);
        if (armor[1] != null)
            livingEntity.getEquipment().setChestplate(armor[1]);
        if (armor[2] != null)
            livingEntity.getEquipment().setLeggings(armor[2]);
        if (armor[3] != null)
            livingEntity.getEquipment().setBoots(armor[3]);
        if (handItems[0] != null)
            if (StaticUtil.is1_8())
                livingEntity.getEquipment().setItemInHand(handItems[0]);
            else
                livingEntity.getEquipment().setItemInMainHand(handItems[0]);
        if (!StaticUtil.is1_8() && handItems[1] != null)
            livingEntity.getEquipment().setItemInOffHand(handItems[1]);
        return this;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("entityType", entityType.toString());
        map.put("customName", customName);
        map.put("health", health);
        map.put("attackSpeed", attackSpeed);
        map.put("speed", speed);
        map.put("maxHealth", maxHealth);
        map.put("armor", armor);
        map.put("handItems", handItems);
        map.put("potionEffects", potionEffects);
        return map;
    }

    public static MobSettings deserialize(Map<String, Object> map) {
        return new MobSettings(map);
    }

}
