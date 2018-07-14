package com.gmail.encryptdev.moreluckyblocks.util;

import com.gmail.encryptdev.moreluckyblocks.MoreLuckyBlocks;
import com.gmail.encryptdev.moreluckyblocks.json.JsonLoader;
import org.bukkit.ChatColor;

/**
 * Created by EncryptDev
 */
public class MessageTranslator {

    private static final JsonLoader JSON_LOADER;
    private static final String PREFIX;

    static {
        JSON_LOADER = MoreLuckyBlocks.getInstance().getJsonLoader();
        PREFIX = translate(JSON_LOADER.getMessageFile().getJsonString("prefix"));
    }

    public static String getMessage(String key) {
        String result = JSON_LOADER.getMessageFile().getJsonString(key);
        if (result.equalsIgnoreCase("-"))
            return "";
        return PREFIX + result;
    }

    public static String getSettingsString(String key) {
        return translate((String) JSON_LOADER.getSettingsFile().getJsonObject("luckyblock-recipe").get(key));
    }

    private static String translate(String toTranslate) {
        return ChatColor.translateAlternateColorCodes('&', toTranslate);
    }

}
