package com.arcunis.lore.custom;

import com.arcunis.lore.Bootstrapper;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public abstract class Item<T extends ItemMeta> implements Listener {

    public final Material material;
    private final Class<T> metaType;
    public final String identifier;
    public final String itemName;
    public final ItemRarity itemRarity;

    /**
     * Represents a custom item
     * @param material Material this item is made of
     * @param metaType Type of ItemMeta to use
     * @param identifier Unique identifier of the item
     * @param itemName Name of the item
     * @param itemRarity Rarity of the item
     * @throws RuntimeException When either the provided material is empty or the provided metaType does not match the meta of the provided material
     */
    public Item(@NotNull Material material, @NotNull Class<T> metaType, @NotNull String identifier, String itemName, ItemRarity itemRarity) throws RuntimeException {
        this.material = material;
        this.metaType = metaType;
        this.identifier = identifier;
        this.itemName = itemName;
        this.itemRarity = itemRarity;
    }

    /**
     * Represents a custom item
     * @param material Material this item is made of
     * @param metaType Type of ItemMeta to use
     * @param identifier Unique identifier of the item
     * @param itemName Name of the item
     * @throws RuntimeException When either the provided material is empty or the provided metaType does not match the meta of the provided material
     */
    public Item(@NotNull Material material, @NotNull Class<T> metaType, @NotNull String identifier, String itemName) throws RuntimeException {
        this.material = material;
        this.metaType = metaType;
        this.identifier = identifier;
        this.itemName = itemName;
        this.itemRarity = ItemRarity.COMMON;
    }

    /**
     * Register the item
     */
    public void register() {
        // If the identifier is invalid, throw error
        if (!Pattern.compile("^[a-z_]+$").matcher(identifier).matches()) {
            Bootstrapper.logger.error(
                    "Failed to register item: '%s'. Invalid identifier"
                            .formatted(identifier)
            );
            throw new RuntimeException(
                    "Failed to register item: '%s'. Invalid identifier"
                            .formatted(identifier)
            );
        }

        // If material cant be obtained, throw error.
        if (material.isEmpty()) {
            Bootstrapper.logger.error(
                    "Failed to register item: '%s'. Material cannot be of type %s"
                            .formatted(identifier, material.toString())
            );
            throw new RuntimeException(
                    "Failed to register item: '%s'. Material cannot be of type %s"
                            .formatted(identifier, material.toString())
            );
        }

        // If provided metaType is not the metaType of the specified material, throw error.
        if (!metaType.isInstance(ItemStack.of(material).getItemMeta())) {
            Bootstrapper.logger.error(
                    "Failed to register item: '%s'. The provided meta type does not match the expected meta type for the material."
                            .formatted(identifier)
            );
            throw new RuntimeException(
                    "Failed to register item: '%s'. The provided meta type does not match the expected meta type for the material."
                            .formatted(identifier)
            );
        }

        // Register events
        Bukkit.getPluginManager().registerEvents(this, Bootstrapper.plugin);
    }

    /**
     * Get a copy of this item's ItemStack
     * @return A new ItemStack
     */
    public ItemStack getItemStack() {
        ItemStack itemStack = ItemStack.of(this.material);
        itemStack.editMeta(meta -> {
            meta.itemName(Component.text(itemName));
            meta.setRarity(itemRarity);
            meta.getPersistentDataContainer().set(new NamespacedKey(Bootstrapper.plugin, "item"), PersistentDataType.STRING, identifier);
        });
        itemStack.editMeta(meta -> getMeta((T) meta));
        return itemStack;
    }

    /**
     * Get an instance of {@code T} to apply to the ItemStack
     * @param meta Mutable instance of {@code T}
     */
    protected abstract void getMeta(T meta);

    /**
     * Check if the specified ItemStack is this custom item
     * @param itemStack The ItemStack to compare against
     * @return True if item is this custom item
     */
    protected boolean isThis(ItemStack itemStack) {
        String identifier = itemStack.getPersistentDataContainer().get(new NamespacedKey(Bootstrapper.plugin, "item"), PersistentDataType.STRING);
        return identifier != null && identifier.equals(this.identifier);
    }

}
