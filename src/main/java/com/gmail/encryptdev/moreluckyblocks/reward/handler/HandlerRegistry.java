package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import com.gmail.encryptdev.moreluckyblocks.util.MLBFileManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by EncryptDev
 */
public class HandlerRegistry {

    private static HandlerRegistry instance;

    public static HandlerRegistry getRegistry() {
        return instance == null ? instance = new HandlerRegistry() : instance;
    }

    private Random random;
    private List<IRewardHandler> customHandler;
    private MLBFileManager mlbFileManager;

    public HandlerRegistry() {
        this.mlbFileManager = new MLBFileManager("handler");
        this.random = new Random();
        if (mlbFileManager.contains("list")) {
            this.customHandler = (List<IRewardHandler>) mlbFileManager.get("list");
        } else {
            this.customHandler = new LinkedList<>();
            this.mlbFileManager.set("list", customHandler);
        }
    }

    public boolean registerCustomHandler(IRewardHandler iRewardHandler) {
        if (customHandler.contains(iRewardHandler))
            return false;
        if(getHandlerRewardObject(iRewardHandler) != null)
            return false;
        MLBFileManager mlbFileManager = new MLBFileManager("handler");
        customHandler.add(iRewardHandler);
        mlbFileManager.set("list", customHandler);
        return true;
    }

    private Object getHandlerRewardObject(IRewardHandler iRewardHandler) {
        for(IRewardHandler handler : customHandler)
            if(handler.equals(iRewardHandler))
                return handler.getRewardObject();
        return null;
    }

    public IRewardHandler getRandomHandler() {
        return customHandler.size() > 0 ? customHandler.get(random.nextInt(customHandler.size())) : null;
    }

}
