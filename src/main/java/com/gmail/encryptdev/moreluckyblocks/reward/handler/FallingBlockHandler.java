package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import com.gmail.encryptdev.moreluckyblocks.reward.fallingblock.FallingBlockType;
import org.bukkit.Location;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.material.MaterialData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class FallingBlockHandler implements IRewardHandler<FallingBlockType> {

    private String handlerName;
    private FallingBlockType fallingBlockType;
    private Location location;
    private int repeat;
    private int high;

    public FallingBlockHandler(String handlerName, FallingBlockType fallingBlockType) {
        this.handlerName = handlerName;
        this.fallingBlockType = fallingBlockType;
        this.repeat = 0;
        this.high = 10;
    }

    public FallingBlockHandler(Map<String, Object> map) {
        this.handlerName = (String) map.get("handlerName");
        this.fallingBlockType = FallingBlockType.valueOf((String) map.get("fallingBlockType"));
        this.repeat = (int) map.get("repeat");
        this.high = (int) map.get("high");
    }

    @Override
    public void handle() {
        if(fallingBlockType == FallingBlockType.PRIMED_TNT) {
            this.location.getWorld().spawn(this.location.clone().add(0, high, 0), TNTPrimed.class);
        } else {
            this.location.getWorld().spawnFallingBlock(this.location.clone().add(0, high, 0), new MaterialData(fallingBlockType.getMaterial()));
        }
    }

    public void setHigh(int high) {
        this.high = high;
    }

    @Override
    public void setLocation(Location paramLocation) {
        this.location = paramLocation;
    }

    @Override
    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    @Override
    public int getRepeat() {
        return repeat;
    }

    @Override
    public FallingBlockType getRewardObject() {
        return fallingBlockType;
    }

    @Override
    public String getHandlerName() {
        return handlerName;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("handlerName", handlerName);
        map.put("fallingBlockType", fallingBlockType.toString());
        map.put("repeat", repeat);
        map.put("high", high);
        return map;
    }

    public static FallingBlockHandler deserialize(Map<String, Object> map) {
        return new FallingBlockHandler(map);
    }
}
