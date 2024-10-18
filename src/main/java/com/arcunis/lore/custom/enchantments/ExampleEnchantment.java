package com.arcunis.lore.custom.enchantments;

import com.arcunis.lore.custom.Enchantment;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemType;

public class ExampleEnchantment extends Enchantment {

    public ExampleEnchantment() {
        super("example_enchantment");
    }

    @Override
    public void builder(EnchantmentRegistryEntry.Builder builder) {
        builder.description(Component.text("Example Item"));
        builder.activeSlots(EquipmentSlotGroup.ANY);
        builder.maxLevel(30);
        builder.anvilCost(1);
    }

}
