package com.arcunis.lore.custom.enchantments;

import com.arcunis.lore.custom.Enchantment;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryFreezeEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExampleEnchantment extends Enchantment {

    public ExampleEnchantment(@NotNull RegistryFreezeEvent<org.bukkit.enchantments.Enchantment, EnchantmentRegistryEntry.Builder> event) {
        super(
                event,
                "example_enchantment",
                Component.translatable("enchantment.arcunis.example_enchantment", "Example Enchantment"),
                List.of(
                        ItemType.STICK
                ),
                1,
                100,
                EnchantmentRegistryEntry.EnchantmentCost.of(1, 1),
                EnchantmentRegistryEntry.EnchantmentCost.of(2, 2),
                5,
                List.of(
                        EquipmentSlotGroup.ANY
                )
        );
    }

}
