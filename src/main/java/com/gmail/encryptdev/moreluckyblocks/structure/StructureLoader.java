package com.gmail.encryptdev.moreluckyblocks.structure;

import com.gmail.encryptdev.moreluckyblocks.MoreLuckyBlocks;
import com.gmail.encryptdev.moreluckyblocks.util.Log;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by EncryptDev
 */
public class StructureLoader {

    private Random random;
    private File dir;
    private List<Structure> loadedStructures;

    public StructureLoader() {
        this.dir = new File(MoreLuckyBlocks.getInstance().getDataFolder(), "/structures/");
        if (!this.dir.exists())
            this.dir.mkdirs();
        this.loadedStructures = new LinkedList<>();
        this.random = new Random();
    }

    public void init() {
        Log.info("Load structures...");
        File[] files = dir.listFiles();
        if (files != null) {
            int counter = 0;
            for (File file : files) {
                if (!file.getName().contains(".schematic")) {
                    Log.warning("File " + file.getName() + " doesn't contains .schematic");
                    continue;
                }
                loadedStructures.add(new Structure(file.getName().replace(".schematic", "")));
                counter++;
            }
            Log.info("Loaded " + counter + " structures");
        }
    }

    public Structure getLoadedStructure(String structureName) {
        for (Structure structure : loadedStructures)
            if (structure.getStructureName().equals(structureName))
                return structure;
        return null;
    }

    public Structure randomStructure() {
        if (loadedStructures.size() == 0)
            return null;
        return loadedStructures.size() == 1 ? loadedStructures.get(0) : loadedStructures.get(random.nextInt(loadedStructures.size() - 1));
    }

}
