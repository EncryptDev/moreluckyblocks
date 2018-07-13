package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import com.gmail.encryptdev.moreluckyblocks.util.MLBFileManager;

import java.util.HashMap;

/**
 * Created by EncryptDev
 */
public class HandlerRegistry {

    private static HandlerRegistry instance;

    private static HandlerRegistry getRegistry() {
        return instance == null ? instance = new HandlerRegistry() : instance;
    }

    private HashMap<String, IRewardHandler> customHandler;
    private MLBFileManager mlbFileManager;

    public HandlerRegistry() {
        this.mlbFileManager = new MLBFileManager("handler");
        if (mlbFileManager.contains("map"))
            this.customHandler = mlbFileManager.get("list", HashMap.class);
        else {
            this.customHandler = new HashMap<>();
            this.mlbFileManager.set("list", customHandler);
        }
    }

    public void registerCustomHandler(String registryName, IRewardHandler iRewardHandler) {
        if (customHandler.containsKey(registryName))
            return;
        MLBFileManager mlbFileManager = new MLBFileManager("handler");
        customHandler.put(registryName, iRewardHandler);
        mlbFileManager.set("map", customHandler);
    }

    public IRewardHandler getCustomHandler(String registryName) {
        return customHandler.get(registryName);
    }


}
