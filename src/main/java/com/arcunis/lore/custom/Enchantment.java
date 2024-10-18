package com.arcunis.lore.custom;

import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
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
