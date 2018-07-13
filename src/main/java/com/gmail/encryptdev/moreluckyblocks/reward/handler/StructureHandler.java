package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import com.gmail.encryptdev.moreluckyblocks.structure.Structure;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class StructureHandler implements IRewardHandler<Structure> {

    private Location location;
    private Structure structure;

    public StructureHandler(Structure structure) {
        this.structure = structure;
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
    public Class<Structure> getProperty() {
        return Structure.class;
    }

    @Override
    public Structure getRewardObject() {
        return structure;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("structure", structure);
        return map;
    }
}
