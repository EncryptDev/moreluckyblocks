package com.gmail.encryptdev.moreluckyblocks.commands;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.LuckyBlockInventory;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.MessageTranslator;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by EncryptDev
 */
public class CommandLuckyBlock extends ACommand {

    @Override
    public void player(Player player, String[] args) {
        if(!player.hasPermission("luckyblocks.*"))
            return;

        if(args.length == 0) {
            AbstractInventory.openInventory(player, new LuckyBlockInventory());
        } else if(args.length == 1) {
            if(args[0].equalsIgnoreCase("help")) {
                player.sendMessage("§6LuckyBlocks §7> Developer: EncryptDev");
                player.sendMessage("§6LuckyBlocks §7> Commands:");
                player.sendMessage("§6LuckyBlocks §7> /lb - Open the GUI, to manage all.");
                player.sendMessage("§6LuckyBlocks §7> /lb help - Show this little messages ;)");
            } else if(args[0].equalsIgnoreCase("debug")) {
                player.getInventory().addItem(ItemCreator.getItem(Material.PISTON_BASE, MessageTranslator.getItemName("lucky-block-name")));
            }
        }

    }

    @Override
    public void console(ConsoleCommandSender consoleCommandSender, String[] args) {

    }
}
