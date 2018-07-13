package com.gmail.encryptdev.moreluckyblocks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by EncryptDev
 */
public abstract class ACommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof ConsoleCommandSender)
            console((ConsoleCommandSender) command, strings);
        else
            player((Player) commandSender, strings);
        return true;
    }

    public abstract void player(Player player, String[] args);

    public abstract void console(ConsoleCommandSender consoleCommandSender, String[] args);
}
