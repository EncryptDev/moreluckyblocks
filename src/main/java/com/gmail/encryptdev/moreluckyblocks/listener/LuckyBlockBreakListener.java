package com.gmail.encryptdev.moreluckyblocks.listener;

import com.gmail.encryptdev.moreluckyblocks.reward.Reward;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.HandlerRegistry;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.IRewardHandler;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by EncryptDev
 */
public class LuckyBlockBreakListener implements Listener {

    @EventHandler
    public void on(BlockBreakEvent event) {
        if (event.getBlock().hasMetadata(StaticUtil.LUCKY_BLOCK_META_DATA)) {
            Reward reward = new Reward(event.getBlock().getLocation());
            IRewardHandler handler = HandlerRegistry.getRegistry().getRandomHandler();
            if(handler == null) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§cERROR > No handler found. Create handler, use /lb");
                return;
            }
            reward.setHandler(HandlerRegistry.getRegistry().getRandomHandler());
            reward.useReward();
        }
    }

}
