package com.gmail.encryptdev.moreluckyblocks.structure;

import com.gmail.encryptdev.moreluckyblocks.MoreLuckyBlocks;
import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class Structure implements ConfigurationSerializable {

    private String structureName;
    private File file;

    public Structure(String structureName) {
        this.structureName = structureName;
        this.file = new File(MoreLuckyBlocks.getInstance().getDataFolder(), "/structures/" + structureName + ".schematic");
    }

    public Structure(Map<String, Object> map) {
        this.structureName = (String) map.get("structureName");
        this.file = new File((String) map.get("file"));
    }

    public void load(Location origin) {
        if (this.file.exists()) {
            EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(origin.getWorld()),
                    WorldEdit.getInstance().getConfiguration().maxChangeLimit);
            SchematicFormat schematicFormat = SchematicFormat.getFormat(file);
            try {
                CuboidClipboard clipboard = schematicFormat.load(file);

                Location cloned = origin.clone();
                Vector placeLocation = new Vector(cloned.clone().subtract(clipboard.getSize().getX() / 2, 0, 0).getX(),
                        cloned.getY(), cloned.clone().subtract(0, 0, clipboard.getSize().getZ() / 2).getZ());

                clipboard.paste(editSession, placeLocation, false);
            } catch (IOException | DataException | MaxChangedBlocksException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStructureName() {
        return structureName;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("structureName", structureName);
        map.put("file", file.getAbsolutePath());
        return map;
    }

    public static Structure deserialize(Map<String, Object> map) {
        return new Structure(map);
    }
}
