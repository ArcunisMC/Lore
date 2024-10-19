package com.arcunis.lore.custom.enchantments;

import com.arcunis.lore.custom.Enchantment;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryFreezeEvent;
import io.papermc.paper.registry.keys.ItemTypeKeys;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import io.papermc.paper.registry.set.RegistryKeySet;
import io.papermc.paper.registry.set.RegistrySet;
import io.papermc.paper.registry.tag.TagKey;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.Registry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExampleEnchantment extends Enchantment {

    public ExampleEnchantment(@NotNull RegistryFreezeEvent<org.bukkit.enchantments.Enchantment, EnchantmentRegistryEntry.Builder> event) {
        super(
                event,
                "example_enchantment",
                Component.translatable("enchantment.minecraft.example_enchantment", "Example Enchantment"),
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
