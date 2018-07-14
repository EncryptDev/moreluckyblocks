package com.gmail.encryptdev.moreluckyblocks.listener.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.LuckyBlockManager;
import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.ChooseArmorTypeInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.ChooseAttributeInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.ChoosePotionInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.ChooseWeaponInventory;
import com.gmail.encryptdev.moreluckyblocks.mob.MobCacheManager;
import com.gmail.encryptdev.moreluckyblocks.mob.MobSettings;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.HandlerRegistry;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.SpawnMobHandler;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by EncryptDev
 */
public class MobSettingsInventoryListener implements Listener {

    private MobCacheManager mobCacheManager;
    private LuckyBlockManager luckyBlockManager;

    public MobSettingsInventoryListener(MobCacheManager mobCacheManager, LuckyBlockManager luckyBlockManager) {
        this.mobCacheManager = mobCacheManager;
        this.luckyBlockManager = luckyBlockManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        if (event.getInventory().getName().equals("§eMob Settings")) {
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
            Player player = (Player) event.getWhoClicked();

            if (displayName.equals("§eMob Armor")) {
                AbstractInventory.openInventory(player, new ChooseArmorTypeInventory());
            } else if (displayName.equals("§eMob Weapons")) {
                AbstractInventory.openInventory(player, new ChooseWeaponInventory());
            } else if (displayName.equals("§eMob Name")) {
                player.closeInventory();
                if(!luckyBlockManager.getChatCommands().containsKey(player)) {
                    luckyBlockManager.getChatCommands().put(player, LuckyBlockManager.CC_MOB_NAME);
                    player.sendMessage(MessageTranslator.getMessage("write-mob-name"));
                }
            } else if (displayName.equals("§eMob Attribute")) {
                AbstractInventory.openInventory(player, new ChooseAttributeInventory());
            } else if (displayName.equals("§eMob Potion")) {
                AbstractInventory.openInventory(player, new ChoosePotionInventory());
            } else if (displayName.equals("§eFinish")) {
                MobSettings mobSettings = mobCacheManager.getPlayerCache().remove(player);
                if (mobSettings != null) {
                    luckyBlockManager.getHandlerNeedRepeat().put(player, new SpawnMobHandler(HandlerRegistry.newHandlerName(), mobSettings));
                    luckyBlockManager.getChatCommands().put(player, LuckyBlockManager.CC_REPEAT);
                    player.sendMessage("§aWrite now the amount of repeat, for the handler (Write 0, for only one)");
                }
                player.closeInventory();
            }
        }
    }

}
