package com.arcunis.lore.customitems;

import com.google.gson.Gson;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class Item implements Listener {

    public Integer id;
    public @Nullable String group;
    public Material material;
    public @Nullable Recipe recipe;
    public Component name;
    public List<Component> lore;
    public boolean unbreakable;
    public Map<Attribute, AttributeModifier> attributes = new HashMap<>();

    public Item(Material material) {
        this.id = CustomItems.items.size();
        this.material = material;
    }

//    public Item(String serialized) {
//        Item data = new Gson().fromJson(serialized, this.getClass());
//        this.id = CustomItems.items.size();
//        this.material = data.material;
//        this.name = data.name;
//        this.lore = data.lore;
//        this.unbreakable = data.unbreakable;
//        this.attributes = data.attributes;
//    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setName(Component name) {
        this.name = name;
    }
    public void setName(String name) {
        this.name = Component.text(name);
    }

    public void setLore(Component[] lore) {
        this.lore = Arrays.asList(lore);
    }
    public void setLore(String[] lore) {
        for (String item : lore) {
            this.lore.add(Component.text(item));
        }
    }

    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
    }

    public void setAttribute(Attribute attribute, Double value, EquipmentSlot slot) {
        attributes.put(attribute, new AttributeModifier(UUID.randomUUID(), attribute.toString(), value, AttributeModifier.Operation.ADD_NUMBER, slot));
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(name);
        if (lore.size() > 0) itemMeta.lore(lore);
        itemMeta.setUnbreakable(unbreakable);
        for (Map.Entry<Attribute, AttributeModifier> entry : attributes.entrySet()) {
            itemMeta.addAttributeModifier(entry.getKey(), entry.getValue());
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

//    public String serialize() {
//        return new Gson().toJson(this);
//    }

}
