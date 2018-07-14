package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import com.gmail.encryptdev.moreluckyblocks.structure.Structure;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class StructureHandler implements IRewardHandler<Structure> {

    private String handlerName;
    private Location location;
    private Structure structure;
    private int repeat;

    public StructureHandler(String handlerName, Structure structure) {
        this.handlerName = handlerName;
        this.structure = structure;
        this.repeat = 0;
    }

    public StructureHandler(Map<String, Object> map) {
        this.handlerName = (String) map.get("handlerName");
        this.structure = (Structure) map.get("structure");
        this.repeat = (int) map.get("repeat");
    }

    @Override
    public void handle() {
        structure.load(location);
    }

    @Override
    public void setLocation(Location paramLocation) {
        this.location = paramLocation;
    }

    @Override
    public Structure getRewardObject() {
        return structure;
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
    public String getHandlerName() {
        return handlerName;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("handlerName", handlerName);
        map.put("structure", structure);
        map.put("repeat", repeat);
        return map;
    }

    public static StructureHandler deserialize(Map<String, Object> map) {
        return new StructureHandler(map);
    }
}
