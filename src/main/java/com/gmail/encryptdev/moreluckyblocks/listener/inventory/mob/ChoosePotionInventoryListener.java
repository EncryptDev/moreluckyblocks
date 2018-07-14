package com.gmail.encryptdev.moreluckyblocks.listener.inventory.mob;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.mob.MobSettingsInventory;
import com.gmail.encryptdev.moreluckyblocks.mob.MobCacheManager;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

/**
 * Created by EncryptDev
 */
public class ChoosePotionInventoryListener implements Listener {

    private MobCacheManager mobCacheManager;

    public ChoosePotionInventoryListener(MobCacheManager mobCacheManager) {
        this.mobCacheManager = mobCacheManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getName().equals(MessageTranslator.getInventoryName("mob-potion"))) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta())
                return;
            String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
            if (displayName.equals("§eRegeneration")) {
                String status = event.getCurrentItem().getItemMeta().getLore().get(0).split(":")[1].trim();
                if (status.equals("§4OFF")) {
                    mobCacheManager.getPlayerCache().get(player).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10000, 1));
                    ItemStack item = ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §2ON"));
                    event.getInventory().setItem(0, item);
                } else if (status.equals("§2ON")) {
                    mobCacheManager.getPlayerCache().get(player).removePotionEffect(PotionEffectType.WEAKNESS);
                    ItemStack item = ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §4OFF"));
                    event.getInventory().setItem(0, item);
                }
            } else if (displayName.equals("§eInvisibility")) {
                String status = event.getCurrentItem().getItemMeta().getLore().get(0).split(":")[1].trim();
                if (status.equals("§4OFF")) {
                    mobCacheManager.getPlayerCache().get(player).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10000, 1));
                    ItemStack item = ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §2ON"));
                    event.getInventory().setItem(1, item);
                } else if (status.equals("§2ON")) {
                    mobCacheManager.getPlayerCache().get(player).removePotionEffect(PotionEffectType.INVISIBILITY);
                    ItemStack item = ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §4OFF"));
                    event.getInventory().setItem(1, item);
                }
            } else if (displayName.equals("§eSpeed")) {
                String status = event.getCurrentItem().getItemMeta().getLore().get(0).split(":")[1].trim();
                if (status.equals("§4OFF")) {
                    mobCacheManager.getPlayerCache().get(player).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 1));
                    ItemStack item = ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §2ON"));
                    event.getInventory().setItem(2, item);
                } else if (status.equals("§2ON")) {
                    mobCacheManager.getPlayerCache().get(player).removePotionEffect(PotionEffectType.SPEED);
                    ItemStack item = ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §4OFF"));
                    event.getInventory().setItem(2, item);
                }
            } else if (displayName.equals("§eStrength")) {
                String status = event.getCurrentItem().getItemMeta().getLore().get(0).split(":")[1].trim();
                if (status.equals("§4OFF")) {
                    mobCacheManager.getPlayerCache().get(player).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10000, 1));
                    ItemStack item = ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §2ON"));
                    event.getInventory().setItem(3, item);
                } else if (status.equals("§2ON")) {
                    mobCacheManager.getPlayerCache().get(player).removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                    ItemStack item = ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §4OFF"));
                    event.getInventory().setItem(3, item);
                }
            } else if (displayName.equals(MessageTranslator.getItemName("page-back"))) {
                AbstractInventory.openInventory(player, new MobSettingsInventory());
            }
        }

    }

}
