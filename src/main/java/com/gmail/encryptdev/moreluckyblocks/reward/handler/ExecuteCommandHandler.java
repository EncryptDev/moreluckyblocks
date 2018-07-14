package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class ExecuteCommandHandler implements IRewardHandler<String> {

    private String command;

    public ExecuteCommandHandler(String command) {
        this.command = command;
    }

    public ExecuteCommandHandler(Map<String, Object> map) {
        this.command = (String) map.get("command");
    }

    @Override
    public void handle() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    @Override
    public void setLocation(Location paramLocation) {

    }

    @Override
    public String getRewardObject() {
        return command;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("command", command);
        return map;
    }

    public static ExecuteCommandHandler deserialize(Map<String, Object> map) {
        return new ExecuteCommandHandler(map);
    }

}
