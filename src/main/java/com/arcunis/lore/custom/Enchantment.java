package com.arcunis.lore.custom;

import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.event.Listener;

public abstract class Enchantment implements Listener {

    /**
     * {@link org.bukkit.enchantments.Enchantment}
     */

    public final String identifier;

    public Enchantment(String identifier) {
        this.identifier = identifier;
    }

    public abstract void builder(EnchantmentRegistryEntry.Builder builder);

}
