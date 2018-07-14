package com.gmail.encryptdev.moreluckyblocks.reward;

import com.gmail.encryptdev.moreluckyblocks.MoreLuckyBlocks;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.IRewardHandler;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.SpawnItemHandler;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.SpawnMobHandler;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.StructureHandler;
import com.gmail.encryptdev.moreluckyblocks.structure.Structure;
import com.gmail.encryptdev.moreluckyblocks.util.Log;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by EncryptDev
 */
public class Reward {

    private IRewardHandler handler;
    private Location location;

    public Reward(Location location) {
        this.location = location;
    }

    public void setHandler(IRewardHandler handler) {
        this.handler = handler;
        this.handler.setLocation(location);
    }

    public void useReward() {
        if (this.handler == null) {
            Log.warning("Can not use reward. RewardHandler is null");
            throw new NullPointerException();
        }

        if (handler.getRepeat() != 0) {
            new BukkitRunnable() {

                private int counter = 0;

                @Override
                public void run() {
                    if (counter >= handler.getRepeat()) {
                        cancel();
                        return;
                    }
                    handler.handle();
                    this.counter++;
                }
            }.runTaskTimer(MoreLuckyBlocks.getInstance(), 0, 20);
        } else {
            handler.handle();
        }

    }

}
