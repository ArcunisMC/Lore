package com.arcunis.lore.custom;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryFreezeEvent;
import io.papermc.paper.registry.set.RegistryKeySet;
import io.papermc.paper.registry.set.RegistrySet;
import io.papermc.paper.registry.tag.TagKey;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

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

    /**
     * Represents a custom event
     * @param event The event that triggers the enchantment registration
     * @param identifier Unique identifier of the enchantment
     * @param description Name of the enchantment
     * @param supportedItems Items the enchantment can be applied to
     * @param primaryItems Items the enchantment can be naturally applied to
     * @param weight Chance of the enchantment showing up in an enchantment table. range of 1 to 1024
     * @param maxLevel Max enchantment level
     * @param minimumCost Minimum cost to apply this enchantment to an item
     * @param maximumCost Maximum cost to apply this enchantment to an item
     * @param anvilCost Cost to put this enchantment on an item using an anvil
     * @param activeSlots Slots in which the enchantment is active
     * @param exclusiveWith Enchantments that prevent this enchantment from being applied
     */
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
        this.primaryItems = primaryItems == null ? null : event.getOrCreateTag(primaryItems);
        this.weight = weight;
        this.maxLevel = maxLevel;
        this.minimumCost = minimumCost;
        this.maximumCost = maximumCost;
        this.anvilCost = anvilCost;
        this.activeSlots = activeSlots;
        this.exclusiveWith = exclusiveWith;
    }

    /**
     * Represents a custom event
     * @param event The event that triggers the enchantment registration
     * @param identifier Unique identifier of the enchantment
     * @param description Name of the enchantment
     * @param supportedItems Items the enchantment can be applied to
     * @param weight Chance of the enchantment showing up in an enchantment table. range of 1 to 1024
     * @param maxLevel Max enchantment level
     * @param minimumCost Minimum cost to apply this enchantment to an item
     * @param maximumCost Maximum cost to apply this enchantment to an item
     * @param anvilCost Cost to put this enchantment on an item using an anvil
     * @param activeSlots Slots in which the enchantment is active
     * @param exclusiveWith Enchantments that prevent this enchantment from being applied
     */
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

    /**
     * Represents a custom event
     * @param event The event that triggers the enchantment registration
     * @param identifier Unique identifier of the enchantment
     * @param description Name of the enchantment
     * @param supportedItems Items the enchantment can be applied to
     * @param primaryItems Items the enchantment can be naturally applied to
     * @param weight Chance of the enchantment showing up in an enchantment table. range of 1 to 1024
     * @param maxLevel Max enchantment level
     * @param minimumCost Minimum cost to apply this enchantment to an item
     * @param maximumCost Maximum cost to apply this enchantment to an item
     * @param anvilCost Cost to put this enchantment on an item using an anvil
     * @param activeSlots Slots in which the enchantment is active
     */
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

    /**
     * Represents a custom event
     * @param event The event that triggers the enchantment registration
     * @param identifier Unique identifier of the enchantment
     * @param description Name of the enchantment
     * @param supportedItems Items the enchantment can be applied to
     * @param weight Chance of the enchantment showing up in an enchantment table. range of 1 to 1024
     * @param maxLevel Max enchantment level
     * @param minimumCost Minimum cost to apply this enchantment to an item
     * @param maximumCost Maximum cost to apply this enchantment to an item
     * @param anvilCost Cost to put this enchantment on an item using an anvil
     * @param activeSlots Slots in which the enchantment is active
     */
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

    /**
     * Represents a custom event
     * @param event The event that triggers the enchantment registration
     * @param identifier Unique identifier of the enchantment
     * @param description Name of the enchantment
     * @param supportedItems Items the enchantment can be applied to
     * @param primaryItems Items the enchantment can be naturally applied to
     * @param weight Chance of the enchantment showing up in an enchantment table. range of 1 to 1024
     * @param maxLevel Max enchantment level
     * @param minimumCost Minimum cost to apply this enchantment to an item
     * @param maximumCost Maximum cost to apply this enchantment to an item
     * @param anvilCost Cost to put this enchantment on an item using an anvil
     * @param activeSlots Slots in which the enchantment is active
     * @param exclusiveWith Enchantments that prevent this enchantment from being applied
     */
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

    /**
     * Represents a custom event
     * @param event The event that triggers the enchantment registration
     * @param identifier Unique identifier of the enchantment
     * @param description Name of the enchantment
     * @param supportedItems Items the enchantment can be applied to
     * @param weight Chance of the enchantment showing up in an enchantment table. range of 1 to 1024
     * @param maxLevel Max enchantment level
     * @param minimumCost Minimum cost to apply this enchantment to an item
     * @param maximumCost Maximum cost to apply this enchantment to an item
     * @param anvilCost Cost to put this enchantment on an item using an anvil
     * @param activeSlots Slots in which the enchantment is active
     * @param exclusiveWith Enchantments that prevent this enchantment from being applied
     */
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

    /**
     * Represents a custom event
     * @param event The event that triggers the enchantment registration
     * @param identifier Unique identifier of the enchantment
     * @param description Name of the enchantment
     * @param supportedItems Items the enchantment can be applied to
     * @param primaryItems Items the enchantment can be naturally applied to
     * @param weight Chance of the enchantment showing up in an enchantment table. range of 1 to 1024
     * @param maxLevel Max enchantment level
     * @param minimumCost Minimum cost to apply this enchantment to an item
     * @param maximumCost Maximum cost to apply this enchantment to an item
     * @param anvilCost Cost to put this enchantment on an item using an anvil
     * @param activeSlots Slots in which the enchantment is active
     */
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

    /**
     * Represents a custom event
     * @param event The event that triggers the enchantment registration
     * @param identifier Unique identifier of the enchantment
     * @param description Name of the enchantment
     * @param supportedItems Items the enchantment can be applied to
     * @param weight Chance of the enchantment showing up in an enchantment table. range of 1 to 1024
     * @param maxLevel Max enchantment level
     * @param minimumCost Minimum cost to apply this enchantment to an item
     * @param maximumCost Maximum cost to apply this enchantment to an item
     * @param anvilCost Cost to put this enchantment on an item using an anvil
     * @param activeSlots Slots in which the enchantment is active
     */
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

    /**
     * Check if the specified item has the specified enchantment
     * @param namespacedKey The enchantment
     * @param itemStack The item stack to check on
     * @return True if the item has the enchantment
     */
    public static boolean hasEnchant(NamespacedKey namespacedKey, ItemStack itemStack) {
        return itemStack.getItemMeta().hasEnchant(RegistryAccess.registryAccess().getRegistry(RegistryKey.ENCHANTMENT).getOrThrow(namespacedKey));
    }

}
