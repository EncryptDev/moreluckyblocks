package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

/**
 * Created by EncryptDev
 */
public interface IRewardHandler<P> extends ConfigurationSerializable {

    void handle();

    void setLocation(Location paramLocation);

    P getRewardObject();

}
