package com.gmail.encryptdev.moreluckyblocks.util;

import java.util.logging.Logger;

/**
 * Created by EncryptDev
 * <p>
 * Log class by EncryptDev
 */
public class Log {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(Log.class.getCanonicalName());
    }

    /**
     * Log a info message
     *
     * @param message - the logged message
     */
    public static void info(String message) {
        LOGGER.info("[MoreLuckyBlocks-LOG] " + message);
    }

    /**
     * Log a warning message
     *
     * @param message - the logged message
     */
    public static void warning(String message) {
        LOGGER.warning("[MoreCrafting-LOG] " + message);
    }

}
