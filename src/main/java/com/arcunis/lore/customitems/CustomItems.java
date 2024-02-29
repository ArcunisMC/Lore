package com.arcunis.lore.customitems;

import com.arcunis.lore.Main;
import com.arcunis.lore.customitems.items.ender.EnderHelmet;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class CustomItems {

    private Main main;

    public static Map<Integer, Item> items = new HashMap<>();

    public CustomItems(Main main) {
        this.main = main;
        this.register();
    }

    public Item getItem(Double id) {
        return CustomItems.items.get(id);
    }

    public void register() {
        Item[] items = new Item[]{

                // Ender
                new EnderHelmet(),

        };

        for (Item item : items) {
            CustomItems.items.put(item.id, item);
            Bukkit.addRecipe(item.recipe);
        }
    }

}
