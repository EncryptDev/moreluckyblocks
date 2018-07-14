package com.gmail.encryptdev.moreluckyblocks.reward.fallingblock;

import org.bukkit.Material;

/**
 * Created by EncryptDev
 */
public enum FallingBlockType {

    ANVIL(Material.ANVIL),
    SAND(Material.SAND),
    GRASS(Material.GRASS),
    BEDROCK(Material.BEDROCK),
    OBSIDIAN(Material.OBSIDIAN),
    PRIMED_TNT(Material.TNT);

    private Material material;

    FallingBlockType(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }
}
