package com.gmail.encryptdev.moreluckyblocks.commands;

import com.gmail.encryptdev.moreluckyblocks.inventory.AbstractInventory;
import com.gmail.encryptdev.moreluckyblocks.inventory.AddNewRewardInventory;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by EncryptDev
 */
public class CommandLuckyBlock extends ACommand {

    @Override
    public void player(Player player, String[] args) {
        if (!player.hasPermission("luckyblocks.*"))
            return;

        if (args.length == 0) {
            AbstractInventory.openInventory(player, new AddNewRewardInventory());
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                player.sendMessage("§6LuckyBlocks §7> Developer: EncryptDev");
                player.sendMessage("§6LuckyBlocks §7> Commands:");
                player.sendMessage("§6LuckyBlocks §7> /lb - Open the GUI, to manage all.");
                player.sendMessage("§6LuckyBlocks §7> /lb help - Show this little messages ;)");
                player.sendMessage("§6LuckyBlocks §7> Note: If the structure spawn in the bottom, delete the schematic file (from WorldEdit, and" +
                        " from the LuckyBlock plugin. And copy it new. But the first block must be the highest, and the second block the lowest block from the " +
                        "schematic)");
            }
        }

    }

    @Override
    public void console(ConsoleCommandSender consoleCommandSender, String[] args) {

    }
}
