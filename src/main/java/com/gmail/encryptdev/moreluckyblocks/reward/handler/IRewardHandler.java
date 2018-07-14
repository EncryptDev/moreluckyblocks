package com.gmail.encryptdev.moreluckyblocks.reward.handler;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

/**
 * Created by EncryptDev
 */
public interface IRewardHandler<P> extends ConfigurationSerializable {

    void handle();

    void setLocation(Location paramLocation);

    void setRepeat(int repeat);

    int getRepeat();

    P getRewardObject();

    String getHandlerName();

}
