package com.arcunis.lore.custom.enchantments;

import com.arcunis.lore.Bootstrapper;
import com.arcunis.lore.custom.Enchantment;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryFreezeEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SoulboundEnchantment extends Enchantment {

    private static final Map<UUID, List<ItemStack>> items = new HashMap<>();

    public SoulboundEnchantment(@NotNull RegistryFreezeEvent<org.bukkit.enchantments.Enchantment, EnchantmentRegistryEntry.Builder> event) {
        super(
                event,
                "soulbound",
                Component.translatable("enchantment.arcunis.soulbound", "Soulbound"),
                RegistryAccess.registryAccess().getRegistry(RegistryKey.ITEM).stream().toList(),
                2,
                1,
                EnchantmentRegistryEntry.EnchantmentCost.of(10, 0),
                EnchantmentRegistryEntry.EnchantmentCost.of(10, 0),
                10,
                List.of(EquipmentSlotGroup.ANY)
        );
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        List<ItemStack> contents = new ArrayList<>();
        for (ItemStack item : player.getInventory()) {
            if (item == null || !item.hasItemMeta() || !hasEnchant(new NamespacedKey(Bootstrapper.plugin, identifier), item)) continue;
            contents.add(item);
            event.getDrops().remove(item);
        }
        items.put(player.getUniqueId(), contents);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        for (ItemStack item : items.get(player.getUniqueId())){
            if (player.getInventory().firstEmpty() > 0) player.getInventory().addItem(item);
            else {
                Item droppedItem = player.getWorld().spawn(player.getLocation(), Item.class);
                droppedItem.setItemStack(item);
            }
        }
        items.remove(player.getUniqueId());
    }

}
