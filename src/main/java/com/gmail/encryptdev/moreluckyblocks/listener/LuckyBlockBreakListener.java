package com.gmail.encryptdev.moreluckyblocks.listener;

import com.gmail.encryptdev.moreluckyblocks.MoreLuckyBlocks;
import com.gmail.encryptdev.moreluckyblocks.reward.Reward;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.HandlerRegistry;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.IRewardHandler;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by EncryptDev
 */
public class LuckyBlockBreakListener implements Listener {

    @EventHandler
    public void on(BlockBreakEvent event) {
        if (event.getBlock().hasMetadata(StaticUtil.LUCKY_BLOCK_META_DATA)) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    Reward reward = new Reward(event.getBlock().getLocation());
                    IRewardHandler handler = HandlerRegistry.getRegistry().getRandomHandler();
                    if (handler == null) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("§cERROR > No handler found. Create handler, use /lb");
                        return;
                    }
                    reward.setHandler(handler);
                    reward.useReward();
                    event.getBlock().removeMetadata(StaticUtil.LUCKY_BLOCK_META_DATA, MoreLuckyBlocks.getInstance());
                }
            }.runTaskLater(MoreLuckyBlocks.getInstance(), 5);
        }
    }

}
