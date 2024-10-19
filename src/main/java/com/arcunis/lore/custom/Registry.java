package com.arcunis.lore.custom;

import com.arcunis.lore.Bootstrapper;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Registry {

    // Custom items
    private final Map<String, Item<?>> items = new HashMap<>();

    public @Nullable Item<?> getItem(String identifier) {
        return items.get(identifier);
    }

    public void registerItem(Item<?> item) {
        items.put(item.identifier, item);
        Bootstrapper.logger.info("Registered item: %s".formatted(item.identifier));
    }

    public Set<String> getAllItemIdentifiers() {
        return items.keySet();
    }

    public Collection<Item<?>> getAllItems() {
        return items.values();
    }

    // Custom enchantments
    private final Map<String, Enchantment> enchantments = new HashMap<>();

    public @Nullable Enchantment getEnchantment(String identifier) {
        return enchantments.get(identifier);
    }

    public void registerEnchantment(Enchantment enchantment) {
        enchantments.put(enchantment.identifier, enchantment);
        Bootstrapper.logger.info("Registered enchantment: %s".formatted(enchantment.identifier));
    }

    public Set<String> getAllEnchantmentIdentifiers() {
        return enchantments.keySet();
    }

    public Collection<Enchantment> getAllEnchantments() {
        return enchantments.values();
    }

}
