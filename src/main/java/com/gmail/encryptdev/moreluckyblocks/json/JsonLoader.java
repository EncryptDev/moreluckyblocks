package com.gmail.encryptdev.moreluckyblocks.json;

/**
 * Created by EncryptDev
 */
public class JsonLoader {

    private JsonFile messageFile;
    private JsonFile settingsFile;

    public JsonLoader() {
        this.messageFile = new JsonFile("messages.json");
        this.settingsFile = new JsonFile("settings.json");
    }

    /**
     * Read all the {@link JsonFile}'s
     */
    public void load() {
        this.messageFile.read();
        this.settingsFile.read();
    }

    public JsonFile getSettingsFile() {
        return settingsFile;
    }

    public JsonFile getMessageFile() {
        return messageFile;
    }
}
