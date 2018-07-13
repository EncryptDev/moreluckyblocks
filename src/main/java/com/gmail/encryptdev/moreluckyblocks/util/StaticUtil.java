package com.gmail.encryptdev.moreluckyblocks.util;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;

/**
 * Created by EncryptDev
 */
public class StaticUtil {

    public static final String LUCKY_BLOCK_META_DATA = "luckyblocks_marked";
    public static final String LUCKY_BLOCK_VALUE = "LUCKY_BLOCK";

    private static final String VERSION;

    static {
        VERSION = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }

    public static boolean is1_8() {
        return VERSION.equalsIgnoreCase("v1_8_R1") || VERSION.equalsIgnoreCase("v1_8_R2") || VERSION.equalsIgnoreCase("v1_8_R3");
    }

    public static boolean isEmpty(Object[] array) {
        Validate.notNull(array);
        for(Object o : array)
            if(o == null)
                return true;

        return false;
    }

}
