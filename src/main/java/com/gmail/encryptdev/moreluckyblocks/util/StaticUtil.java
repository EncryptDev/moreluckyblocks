package com.gmail.encryptdev.moreluckyblocks.util;

import org.bukkit.Bukkit;

/**
 * Created by EncryptDev
 */
public class StaticUtil {

    public static final String LUCKY_BLOCK_META_DATA = "luckyblocks_marked";
    public static final String LUCKY_BLOCK_VALUE = "LUCKY_BLOCK";

    public static final String VERSION;

    static {
        VERSION = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }

    public static boolean is1_8() {
        return VERSION.equalsIgnoreCase("v1_8_R1") || VERSION.equalsIgnoreCase("v1_8_R2") || VERSION.equalsIgnoreCase("v1_8_R3");
    }

    public static String normalToEnum(String enumRaw) {
        return enumRaw.replace(" ", "_").toUpperCase();
    }

    public static String enumToNormal(Enum enumValue) {
        String etvStr = enumValue.toString();
        String lowerCase = etvStr.toLowerCase();
        String result;

        if (lowerCase.contains("_")) {
            char firstIndex = lowerCase.charAt(0);
            char afterSplit = lowerCase.charAt(lowerCase.indexOf("_") + 1);

            String restLeft = lowerCase.substring(1, lowerCase.indexOf("_"));
            String restRight = lowerCase.substring(lowerCase.indexOf("_") + 2, lowerCase.length());
            result = Character.toUpperCase(firstIndex) + restLeft + " " + Character.toUpperCase(afterSplit) + restRight;
        } else {
            char firstIndex = lowerCase.charAt(0);
            String rest = lowerCase.substring(1, lowerCase.length());
            result = Character.toUpperCase(firstIndex) + rest;
        }
        return result;
    }

    public static int calculateMaxPages(int itemAmount) {
        int page = 1;
        for (; itemAmount > 45; itemAmount -= 45) page++;
        return page;
    }

}
