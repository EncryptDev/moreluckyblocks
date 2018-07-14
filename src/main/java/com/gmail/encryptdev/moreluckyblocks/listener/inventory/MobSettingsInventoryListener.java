package com.gmail.encryptdev.moreluckyblocks.listener.inventory;

import com.gmail.encryptdev.moreluckyblocks.LuckyBlockManager;
import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.CounterInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.PutInventory;
import com.gmail.encryptdev.moreluckyblocks.mob.MobCacheManager;
import com.gmail.encryptdev.moreluckyblocks.mob.MobSettings;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.HandlerRegistry;
import com.gmail.encryptdev.moreluckyblocks.reward.handler.SpawnMobHandler;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import com.gmail.encryptdev.moreluckyblocks.util.StaticUtil;
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

            if (displayName.equals("§eMob Name")) {
                player.closeInventory();
                if (!luckyBlockManager.getChatCommands().containsKey(player)) {
                    luckyBlockManager.getChatCommands().put(player, LuckyBlockManager.CC_MOB_NAME);
                    player.sendMessage(MessageTranslator.getMessage("write-mob-name"));
                }
            } else if (displayName.equals("§eFinish")) {
                MobSettings mobSettings = mobCacheManager.getPlayerCache().remove(player);
                if (mobSettings != null) {
                    luckyBlockManager.getHandlerNeedRepeat().put(player, new SpawnMobHandler(HandlerRegistry.newHandlerName(), mobSettings));
                    luckyBlockManager.getChatCommands().put(player, LuckyBlockManager.CC_REPEAT);
                    player.sendMessage("§aWrite now the amount of repeat, for the handler (Write 0, for only one)");
                }
                player.closeInventory();
            } else if (displayName.equals("§eHelmet")) {
                player.closeInventory();
                event.setCancelled(true);
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.HELMET));
            } else if (displayName.equals("§eChestplate")) {
                player.closeInventory();
                event.setCancelled(true);
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.CHESTPLATE));
            } else if (displayName.equals("§eLeggings")) {
                player.closeInventory();
                event.setCancelled(true);
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.LEGGINGS));
            } else if (displayName.equals("§eBoots")) {
                player.closeInventory();
                event.setCancelled(true);
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.BOOTS));
            } else if (displayName.equals("§eMain Hand")) {
                player.closeInventory();
                event.setCancelled(true);
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.MAIN_HAND));
            } else if (displayName.equals("§eOff Hand") && !StaticUtil.is1_8()) {
                player.closeInventory();
                event.setCancelled(true);
                AbstractInventory.openInventory(player, new PutInventory(PutInventory.PutType.OFF_HAND));
            } else if (displayName.equals("§eHealth")) {
                player.closeInventory();
                event.setCancelled(true);
                AbstractInventory.openInventory(player, new CounterInventory("Health"));
            } else if (displayName.equals("§eSpeed")) {
                player.closeInventory();
                event.setCancelled(true);
                AbstractInventory.openInventory(player, new CounterInventory("Speed"));
            } else if (displayName.equals("§eAttack Damage")) {
                player.closeInventory();
                event.setCancelled(true);
                AbstractInventory.openInventory(player, new CounterInventory("Attack Damage"));
            } else if (displayName.equals("§eRegeneration")) {
                event.setCancelled(true);

                String status = event.getCurrentItem().getItemMeta().getLore().get(0).split(":")[1].trim();
                if (status.equals("§4§lOFF")) {
                    mobCacheManager.getPlayerCache().get(player).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10000, 1));
                    event.getInventory().setItem(event.getSlot(), ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §2§lON")));
                } else if(status.equals("§2§lON")) {
                    mobCacheManager.getPlayerCache().get(player).removePotionEffect(PotionEffectType.WEAKNESS);
                    event.getInventory().setItem(event.getSlot(), ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §4§lOFF")));
                }

            } else if (displayName.equals("§eInvisibility")) {
                event.setCancelled(true);
                String status = event.getCurrentItem().getItemMeta().getLore().get(0).split(":")[1].trim();
                if (status.equals("§4§lOFF")) {
                    mobCacheManager.getPlayerCache().get(player).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10000, 1));
                    event.getInventory().setItem(event.getSlot(), ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §2§lON")));
                } else if(status.equals("§2§lON")) {
                    mobCacheManager.getPlayerCache().get(player).removePotionEffect(PotionEffectType.INVISIBILITY);
                    event.getInventory().setItem(event.getSlot(), ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §4§lOFF")));
                }
            } else if (displayName.equals("§eStrength")) {
                event.setCancelled(true);
                String status = event.getCurrentItem().getItemMeta().getLore().get(0).split(":")[1].trim();
                if (status.equals("§4§lOFF")) {
                    mobCacheManager.getPlayerCache().get(player).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10000, 1));
                    event.getInventory().setItem(event.getSlot(), ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §2§lON")));
                } else if(status.equals("§2§lON")) {
                    mobCacheManager.getPlayerCache().get(player).removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                    event.getInventory().setItem(event.getSlot(), ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §4§lOFF")));
                }
            } else if (displayName.equals("§eSpeed")) {
                event.setCancelled(true);
                String status = event.getCurrentItem().getItemMeta().getLore().get(0).split(":")[1].trim();
                if (status.equals("§4§lOFF")) {
                    mobCacheManager.getPlayerCache().get(player).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 1));
                    event.getInventory().setItem(event.getSlot(), ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §2§lON")));
                } else if(status.equals("§2§lON")) {
                    mobCacheManager.getPlayerCache().get(player).removePotionEffect(PotionEffectType.SPEED);
                    event.getInventory().setItem(event.getSlot(), ItemCreator.changeLore(event.getCurrentItem(), Arrays.asList("§eStatus: §4§lOFF")));
                }
            }
        }
    }

}
