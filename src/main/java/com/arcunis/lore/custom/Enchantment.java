package com.arcunis.lore.custom;

import com.arcunis.lore.Bootstrapper;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryFreezeEvent;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import io.papermc.paper.registry.set.RegistryKeySet;
import io.papermc.paper.registry.set.RegistrySet;
import io.papermc.paper.registry.tag.TagKey;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

//TODO Add documentation
public abstract class Enchantment implements Listener {

    public final @NotNull String identifier;
    public final @NotNull Component description;
    public final @NotNull RegistryKeySet<ItemType> supportedItems;
    public final @Nullable RegistryKeySet<ItemType> primaryItems;
    public final int weight;
    public final int maxLevel;
    public final @NotNull EnchantmentRegistryEntry.EnchantmentCost minimumCost;
    public final @NotNull EnchantmentRegistryEntry.EnchantmentCost maximumCost;
    public final int anvilCost;
    public final @NotNull List<EquipmentSlotGroup> activeSlots;
    public final @Nullable RegistryKeySet<org.bukkit.enchantments.Enchantment> exclusiveWith;

    public Enchantment(
            @NotNull RegistryFreezeEvent<org.bukkit.enchantments.Enchantment, EnchantmentRegistryEntry.Builder> event,
            @NotNull String identifier,
            @NotNull Component description,
            @NotNull TagKey<ItemType> supportedItems,
            @Nullable TagKey<ItemType> primaryItems,
            @NotNull int weight,
            @NotNull int maxLevel,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost minimumCost,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost maximumCost,
            @NotNull int anvilCost,
            @NotNull List<EquipmentSlotGroup> activeSlots,
            @Nullable RegistryKeySet<org.bukkit.enchantments.Enchantment> exclusiveWith
    ) {
        this.identifier = identifier;
        this.description = description;
        this.supportedItems = event.getOrCreateTag(supportedItems);
        this.primaryItems = event.getOrCreateTag(primaryItems);
        this.weight = weight;
        this.maxLevel = maxLevel;
        this.minimumCost = minimumCost;
        this.maximumCost = maximumCost;
        this.anvilCost = anvilCost;
        this.activeSlots = activeSlots;
        this.exclusiveWith = exclusiveWith;
    }

    public Enchantment(
            @NotNull RegistryFreezeEvent<org.bukkit.enchantments.Enchantment, EnchantmentRegistryEntry.Builder> event,
            @NotNull String identifier,
            @NotNull Component description,
            @NotNull TagKey<ItemType> supportedItems,
            @NotNull int weight,
            @NotNull int maxLevel,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost minimumCost,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost maximumCost,
            @NotNull int anvilCost,
            @NotNull List<EquipmentSlotGroup> activeSlots,
            @Nullable RegistryKeySet<org.bukkit.enchantments.Enchantment> exclusiveWith
    ) {
        this(event, identifier, description, supportedItems, null, weight, maxLevel, minimumCost, maximumCost, anvilCost, activeSlots, exclusiveWith);
    }

    public Enchantment(
            @NotNull RegistryFreezeEvent<org.bukkit.enchantments.Enchantment, EnchantmentRegistryEntry.Builder> event,
            @NotNull String identifier,
            @NotNull Component description,
            @NotNull TagKey<ItemType> supportedItems,
            @Nullable TagKey<ItemType> primaryItems,
            @NotNull int weight,
            @NotNull int maxLevel,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost minimumCost,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost maximumCost,
            @NotNull int anvilCost,
            @NotNull List<EquipmentSlotGroup> activeSlots
    ) {
        this(event, identifier, description, supportedItems, primaryItems, weight, maxLevel, minimumCost, maximumCost, anvilCost, activeSlots, null);
    }

    public Enchantment(
            @NotNull RegistryFreezeEvent<org.bukkit.enchantments.Enchantment, EnchantmentRegistryEntry.Builder> event,
            @NotNull String identifier,
            @NotNull Component description,
            @NotNull TagKey<ItemType> supportedItems,
            @NotNull int weight,
            @NotNull int maxLevel,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost minimumCost,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost maximumCost,
            @NotNull int anvilCost,
            @NotNull List<EquipmentSlotGroup> activeSlots
    ) {
        this(event, identifier, description, supportedItems, null, weight, maxLevel, minimumCost, maximumCost, anvilCost, activeSlots, null);
    }

    public Enchantment(
            @NotNull RegistryFreezeEvent<org.bukkit.enchantments.Enchantment, EnchantmentRegistryEntry.Builder> event,
            @NotNull String identifier,
            @NotNull Component description,
            @NotNull List<ItemType> supportedItems,
            @Nullable List<ItemType> primaryItems,
            @NotNull int weight,
            @NotNull int maxLevel,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost minimumCost,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost maximumCost,
            @NotNull int anvilCost,
            @NotNull List<EquipmentSlotGroup> activeSlots,
            @Nullable RegistryKeySet<org.bukkit.enchantments.Enchantment> exclusiveWith
    ) {
        this.identifier = identifier;
        this.description = description;

        this.supportedItems = RegistrySet.keySet(
                RegistryKey.ITEM,
                supportedItems.stream().map(
                        itemType -> TypedKey.create(RegistryKey.ITEM, itemType.key())
                ).toList()
        );

        this.primaryItems = primaryItems != null ?
                RegistrySet.keySet(
                        RegistryKey.ITEM,
                        primaryItems.stream()
                                .map(itemType -> TypedKey.create(RegistryKey.ITEM, itemType.key()))
                                .toList()
                ) : null;

        this.weight = weight;
        this.maxLevel = maxLevel;
        this.minimumCost = minimumCost;
        this.maximumCost = maximumCost;
        this.anvilCost = anvilCost;
        this.activeSlots = activeSlots;
        this.exclusiveWith = exclusiveWith;
    }

    public Enchantment(
            @NotNull RegistryFreezeEvent<org.bukkit.enchantments.Enchantment, EnchantmentRegistryEntry.Builder> event,
            @NotNull String identifier,
            @NotNull Component description,
            @NotNull List<ItemType> supportedItems,
            @NotNull int weight,
            @NotNull int maxLevel,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost minimumCost,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost maximumCost,
            @NotNull int anvilCost,
            @NotNull List<EquipmentSlotGroup> activeSlots,
            @Nullable RegistryKeySet<org.bukkit.enchantments.Enchantment> exclusiveWith
    ) {
        this(event, identifier, description, supportedItems, null, weight, maxLevel, minimumCost, maximumCost, anvilCost, activeSlots, exclusiveWith);
    }

    public Enchantment(
            @NotNull RegistryFreezeEvent<org.bukkit.enchantments.Enchantment, EnchantmentRegistryEntry.Builder> event,
            @NotNull String identifier,
            @NotNull Component description,
            @NotNull List<ItemType> supportedItems,
            @Nullable List<ItemType> primaryItems,
            @NotNull int weight,
            @NotNull int maxLevel,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost minimumCost,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost maximumCost,
            @NotNull int anvilCost,
            @NotNull List<EquipmentSlotGroup> activeSlots
    ) {
        this(event, identifier, description, supportedItems, primaryItems, weight, maxLevel, minimumCost, maximumCost, anvilCost, activeSlots, null);
    }

    public Enchantment(
            @NotNull RegistryFreezeEvent<org.bukkit.enchantments.Enchantment, EnchantmentRegistryEntry.Builder> event,
            @NotNull String identifier,
            @NotNull Component description,
            @NotNull List<ItemType> supportedItems,
            @NotNull int weight,
            @NotNull int maxLevel,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost minimumCost,
            @NotNull EnchantmentRegistryEntry.EnchantmentCost maximumCost,
            @NotNull int anvilCost,
            @NotNull List<EquipmentSlotGroup> activeSlots
    ) {
        this(event, identifier, description, supportedItems, null, weight, maxLevel, minimumCost, maximumCost, anvilCost, activeSlots, null);
    }

    public void builder(EnchantmentRegistryEntry.Builder builder) {
        builder.description(description);
        builder.supportedItems(supportedItems);
        builder.primaryItems(primaryItems);
        builder.weight(weight);
        builder.maxLevel(maxLevel);
        builder.minimumCost(minimumCost);
        builder.maximumCost(maximumCost);
        builder.anvilCost(anvilCost);
        builder.activeSlots(activeSlots);
        if (exclusiveWith != null) builder.exclusiveWith(exclusiveWith);
    }

    public static boolean hasEnchant(String identifier, ItemStack itemStack) {
        return itemStack.getItemMeta().hasEnchant(RegistryAccess.registryAccess().getRegistry(RegistryKey.ENCHANTMENT).getOrThrow(new NamespacedKey(Bootstrapper.NAMESPACE, identifier)));
    }

}
