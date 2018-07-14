package com.gmail.encryptdev.moreluckyblocks.mob;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class MobCacheManager {

    private Map<Player, MobSettings> playerCache;

    public MobCacheManager() {
        this.playerCache = new HashMap<>();
    }

    public Map<Player, MobSettings> getPlayerCache() {
        return playerCache;
    }
}
