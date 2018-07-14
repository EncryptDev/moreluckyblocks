package com.gmail.encryptdev.moreluckyblocks.listener.inventory;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.ListInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.MobSettingsInventory;
import com.gmail.encryptdev.moreluckyblocks.mob.MobCacheManager;
import com.gmail.encryptdev.moreluckyblocks.mob.MobSettings;
import com.gmail.encryptdev.moreluckyblocks.util.EntityTypeUtil;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
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

    public ListInventoryListener(MobCacheManager mobCacheManager) {
        this.mobCacheManager = mobCacheManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getName().startsWith(MessageTranslator.getInventoryName("mob-list"))) {
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
            String[] splitted = event.getInventory().getName().split("#");
            int page = Integer.parseInt(splitted[splitted.length - 1]);

            if (event.getInventory().getName().equals(MessageTranslator.getInventoryName("mob-list") + " #" + page)) {
                event.setCancelled(true);
                int maxPages = StaticUtil.calculateMaxPages(EntityTypeUtil.getEntityTypes().length);
                if (displayName.equals(MessageTranslator.getItemName("page-next"))) {
                    if (!(page >= maxPages))
                        AbstractInventory.openInventory(player, new ListInventory(MessageTranslator.getInventoryName("mob-list"),
                                EntityTypeUtil.getEntityTypes(), page + 1));
                    return;
                } else if (displayName.equals(MessageTranslator.getItemName("page-back"))) {
                    if (!(page <= 1))
                        AbstractInventory.openInventory(player, new ListInventory(MessageTranslator.getInventoryName("mob-list"),
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

        }

    }

}
