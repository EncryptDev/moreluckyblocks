package com.gmail.encryptdev.moreluckyblocks.listener;

import com.gmail.encryptdev.moreluckyblocks.MoreLuckyBlocks;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by EncryptDev
 */
public class LuckyBlockPlaceListener implements Listener {

    @EventHandler
    public void on(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        boolean res = false;

        if (StaticUtil.is1_8())
            res = player.getInventory().getItemInHand().hasItemMeta() &&
                    player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(MessageTranslator.getSettingsString("name"));
        else
            res = player.getInventory().getItemInMainHand().hasItemMeta() &&
                    player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(MessageTranslator.getSettingsString("name"));

        if (res)
            event.getBlock().setMetadata(StaticUtil.LUCKY_BLOCK_META_DATA, new FixedMetadataValue(MoreLuckyBlocks.getInstance(), StaticUtil.LUCKY_BLOCK_VALUE));

    }

}
