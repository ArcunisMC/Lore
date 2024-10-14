package com.arcunis.lore.custom;

import com.arcunis.lore.Lore;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Registry {

    private final Lore plugin;

    public Registry(Lore plugin) {
        this.plugin = plugin;
    }

    // Custom items
    private final Map<String, Item<?>> items = new HashMap<>();

    public @Nullable Item<?> getItem(String identifier) {
        return items.get(identifier);
    }

    public void registerItem(Item<?> item) {
        items.put(item.identifier, item);
        plugin.logger.info("Registered item: %s".formatted(item.identifier));
    }

    public Set<String> getAllIdentifiers() {
        return items.keySet();
    }

    public Collection<Item<?>> getAllItems() {
        return items.values();
    }

}
