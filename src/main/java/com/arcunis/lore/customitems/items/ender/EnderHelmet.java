package com.arcunis.lore.customitems.items.ender;

import com.arcunis.lore.customitems.Item;
import com.arcunis.lore.events.OnEquipEvent;
import com.arcunis.lore.events.OnUnequipEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnderHelmet extends Item {

    public EnderHelmet() {
        super(Material.NETHERITE_HELMET);
        setName(Component.text("Ender Infused Helmet"));
        setLore(new Component[]{});
    }

    @EventHandler
    public void onEquip(OnEquipEvent event) {
        ItemStack item = event.getItem();
        if (!item.getItemMeta().hasCustomModelData() || item.getItemMeta().getCustomModelData() != this.id) return;
        Player player = event.getPlayer();
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 0, false, false, false));
    }

    @EventHandler
    public void onUnequip(OnUnequipEvent event) {
        ItemStack item = event.getItem();
        if (!item.getItemMeta().hasCustomModelData() || item.getItemMeta().getCustomModelData() != this.id) return;
        Player player = event.getPlayer();
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }
}