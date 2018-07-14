package com.gmail.encryptdev.moreluckyblocks.util;

import org.bukkit.entity.EntityType;

/**
 * Created by EncryptDev
 */
public class EntityTypeUtil {

    private static final EntityType[] ENTITY_TYPES_1_8;
    private static final EntityType[] ENTITY_TYPES_1_9;
    private static final EntityType[] ENTITY_TYPES_1_10;
    private static final EntityType[] ENTITY_TYPES_1_11;
    private static final EntityType[] ENTITY_TYPES_1_12;

    static {
        ENTITY_TYPES_1_8 = new EntityType[]{EntityType.CREEPER, EntityType.SKELETON, EntityType.SPIDER, EntityType.GIANT, EntityType.ZOMBIE,
                EntityType.SLIME, EntityType.GHAST, EntityType.PIG_ZOMBIE, EntityType.ENDERMAN, EntityType.CAVE_SPIDER, EntityType.SILVERFISH,
                EntityType.BLAZE, EntityType.MAGMA_CUBE, EntityType.ENDER_DRAGON, EntityType.WITHER, EntityType.BAT, EntityType.WITCH, EntityType.ENDERMITE,
                EntityType.GUARDIAN, EntityType.PIG, EntityType.SHEEP, EntityType.COW, EntityType.CHICKEN, EntityType.SQUID, EntityType.WOLF, EntityType.MUSHROOM_COW,
                EntityType.SNOWMAN, EntityType.OCELOT, EntityType.IRON_GOLEM, EntityType.HORSE, EntityType.RABBIT, EntityType.VILLAGER};
        ENTITY_TYPES_1_9 = compact(ENTITY_TYPES_1_8, EntityType.SHULKER);
        ENTITY_TYPES_1_10 = compact(ENTITY_TYPES_1_9, EntityType.POLAR_BEAR);
        ENTITY_TYPES_1_11 = compact(ENTITY_TYPES_1_10, EntityType.STRAY, EntityType.HUSK, EntityType.ZOMBIE_VILLAGER, EntityType.SKELETON_HORSE,
                EntityType.ZOMBIE_HORSE, EntityType.DONKEY, EntityType.MULE, EntityType.EVOKER, EntityType.VEX, EntityType.VINDICATOR, EntityType.LLAMA);
        ENTITY_TYPES_1_12 = compact(ENTITY_TYPES_1_11, EntityType.ILLUSIONER, EntityType.PARROT);
    }

    public static EntityType[] getEntityTypes() {
        switch (StaticUtil.VERSION) {
            case "v1_8_R1":
            case "v1_8_R2":
            case "v1_8_R3":
                return ENTITY_TYPES_1_8;
            case "v1_9_R1":
                return ENTITY_TYPES_1_9;
            case "v1_10_R1":
                return ENTITY_TYPES_1_10;
            case "v1_11_R1":
                return ENTITY_TYPES_1_11;
            case "v1_12_R1":
                return ENTITY_TYPES_1_12;
        }
        return ENTITY_TYPES_1_8;
    }

    private static EntityType[] compact(EntityType[] array, EntityType... newValues) {
        EntityType[] result = new EntityType[array.length + newValues.length];
        int index = 0;
        for(int i = 0; i < array.length; i++) {
            result[index] = array[i];
            index += 1;
        }

        for(int i = 0; i < newValues.length; i++) {
            result[index] = newValues[i];
            index += 1;
        }
        return result;
    }

}
