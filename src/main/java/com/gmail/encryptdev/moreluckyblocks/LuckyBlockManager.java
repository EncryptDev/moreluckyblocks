package com.gmail.encryptdev.moreluckyblocks;

import com.gmail.encryptdev.moreluckyblocks.json.JsonLoader;
import com.gmail.encryptdev.moreluckyblocks.util.ItemCreator;
import com.gmail.encryptdev.moreluckyblocks.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class LuckyBlockManager {

    private JsonLoader jsonLoader;

    public LuckyBlockManager(JsonLoader jsonLoader) {
        this.jsonLoader = jsonLoader;
    }

    public void init() {
        this.loadLuckyBlockRecipe();
    }

    private void loadLuckyBlockRecipe() {
        Log.info("Load custom workbench recipe...");
        JSONObject customRecipeObj = jsonLoader.getSettingsFile().getJsonObject("luckyblock-recipe");
        JSONObject shape = (JSONObject) customRecipeObj.get("shape");
        String[] rows = new String[]{(String) shape.get("row-1"), (String) shape.get("row-2"), (String) shape.get("row-3")};
        JSONArray ingredients = (JSONArray) shape.get("ingredients");
        Map<Character, Material> ingredientsMap = new HashMap<>();

        Iterator<String> ingredientsIterator = ingredients.iterator();
        while (ingredientsIterator.hasNext()) {
            String ingredient = ingredientsIterator.next();
            String[] data = ingredient.split(":");
            if (data.length != 2) {
                Log.warning("Can not load custom workbench recipe, ingredient data has not the right format. Right format: C:MATERIAL" +
                        " [C = Character, MATERIAL = The item material]");
                return;
            }
            Character character = data[0].toCharArray()[0];
            Material material = Material.getMaterial(data[1]);
            if (material == null) {
                Log.warning("Can not load custom workbench recipe, the material [" + data[1] + "] doesn't exist");
                return;
            }
            ingredientsMap.put(character, material);
        }

        if (ingredientsMap.isEmpty()) {
            Log.warning("Can not load custom workbench recipe, ingredient have a size of 0");
            return;
        }


        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("custom_workbench"), ItemCreator.getItem(Material.PISTON_BASE,
                ChatColor.translateAlternateColorCodes('&', (String) customRecipeObj.get("name"))));
        recipe.shape(rows);
        for (Character c : ingredientsMap.keySet())
            recipe.setIngredient(c, ingredientsMap.get(c));

        Bukkit.addRecipe(recipe);
    }

}
