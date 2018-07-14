package com.gmail.encryptdev.moreluckyblocks.listener.inventory;

import com.gmail.encryptdev.moreluckyblocks.LuckyBlockManager;
import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.ListInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.MobSettingsInventory;
import com.gmail.encryptdev.moreluckyblocks.mob.MobCacheManager;
import com.gmail.encryptdev.moreluckyblocks.mob.MobSettings;
import com.gmail.encryptdev.moreluckyblocks.reward.fallingblock.FallingBlockType;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.FallingBlockHandler;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.HandlerRegistry;
import com.gmail.encryptdev.moreluckyblocks.util.EntityTypeUtil;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by EncryptDev
 */
public class ListInventoryListener implements Listener {

    private MobCacheManager mobCacheManager;
    private LuckyBlockManager luckyBlockManager;

    public ListInventoryListener(MobCacheManager mobCacheManager, LuckyBlockManager luckyBlockManager) {
        this.mobCacheManager = mobCacheManager;
        this.luckyBlockManager = luckyBlockManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getName().startsWith("§eEntity List")) {
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
            String[] splitted = event.getInventory().getName().split("#");
            int page = Integer.parseInt(splitted[splitted.length - 1]);

            if (event.getInventory().getName().equals("§eEntity List #" + page)) {
                event.setCancelled(true);
                int maxPages = StaticUtil.calculateMaxPages(EntityTypeUtil.getEntityTypes().length);
                if (displayName.equals("§eNext")) {
                    if (!(page >= maxPages))
                        AbstractInventory.openInventory(player, new ListInventory("§eEntity List",
                                EntityTypeUtil.getEntityTypes(), page + 1));
                    return;
                } else if (displayName.equals("§eBack")) {
                    if (!(page <= 1))
                        AbstractInventory.openInventory(player, new ListInventory("§eEntity List",
                                EntityTypeUtil.getEntityTypes(), page - 1));
                    return;
                }

                if (displayName.equals("§0"))
                    return;

                String entityNameRaw = ChatColor.stripColor(displayName);
                String entityName = StaticUtil.normalToEnum(entityNameRaw);
                mobCacheManager.getPlayerCache().put(player, new MobSettings(EntityType.valueOf(entityName)));
                AbstractInventory.openInventory(player, new MobSettingsInventory());
            }

        } else if (event.getInventory().getName().startsWith("§eFalling Blocks")) {
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
            String[] splitted = event.getInventory().getName().split("#");
            int page = Integer.parseInt(splitted[splitted.length - 1]);

            if (event.getInventory().getName().equals("§eFalling Blocks #" + page)) {
                event.setCancelled(true);
                if (displayName.equals("§0") || displayName.equals("§eBack") || displayName.equals("§eNext"))
                    return;
                String raw = ChatColor.stripColor(displayName);
                String name = StaticUtil.normalToEnum(raw);
                if (!luckyBlockManager.getChatCommands().containsKey(player)) {
                    player.closeInventory();
                    luckyBlockManager.getHandlerNeedRepeat().put(player, new FallingBlockHandler(HandlerRegistry.newHandlerName(), FallingBlockType.valueOf(name)));
                    luckyBlockManager.getChatCommands().put(player, LuckyBlockManager.CC_HIGH);
                    player.sendMessage("§aWrite now the high for the handler, where the falling blocks spawn");
                }
            }
        }

    }

}
