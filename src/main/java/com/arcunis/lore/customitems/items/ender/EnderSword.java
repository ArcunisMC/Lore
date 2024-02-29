package com.arcunis.lore.customitems.items.ender;

import com.arcunis.lore.customitems.Item;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

public class EnderSword extends Item {

    public EnderSword() {
        super(Material.NETHERITE_SWORD);
        setName(Component.text("Ender Sword"));
    }

}
