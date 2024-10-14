package com.arcunis.lore.custom;

import com.arcunis.lore.Lore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public abstract class Item<T extends ItemMeta> implements Listener {

    private final Lore plugin;
    public final Material material;
    public final String identifier;

    /**
     * Represents a custom item
     * @param plugin This plugins main class
     * @param material Material this item is made of
     * @param metaType Type of ItemMeta to use
     * @param identifier Unique identifier of the item
     * @throws RuntimeException When either the provided material is empty or the provided metaType does not match the meta of the provided material
     */
    protected Item(Lore plugin, Material material, Class<T> metaType, String identifier) throws RuntimeException {
        this.plugin = plugin;
        this.material = material;
        this.identifier = identifier;

        // If material cant be obtained, throw error.
        if (material.isEmpty()) {
            plugin.logger.warning(
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
            plugin.logger.warning(
                    "Failed to register item: '%s'. The provided meta type does not match the expected meta type for the material."
                            .formatted(identifier)
            );
            throw new RuntimeException(
                    "Failed to register item: '%s'. The provided meta type does not match the expected meta type for the material."
                            .formatted(identifier)
            );
        }

        // Register events
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Get a copy of this item's ItemStack
     * @return A new ItemStack
     */
    public ItemStack getItemStack() {
        ItemStack itemStack = ItemStack.of(this.material);
        itemStack.editMeta(meta -> getMeta((T) meta));
        itemStack.editMeta(meta -> {
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "identifier"), PersistentDataType.STRING, identifier);
        });
        return itemStack;
    }

    /**
     * Get an instance of {@code T} to apply to the ItemStack
     * @param meta Mutable instance of {@code T}
     */
    public abstract void getMeta(T meta);

    /**
     * Check if the specified ItemStack is this custom item
     * @param itemStack The ItemStack to compare against
     * @return True if item is this custom item
     */
    protected boolean isThis(ItemStack itemStack) {
        String identifier = itemStack.getPersistentDataContainer().get(new NamespacedKey(plugin, "identifier"), PersistentDataType.STRING);
        return identifier != null && identifier.equals(this.identifier);
    }

}
