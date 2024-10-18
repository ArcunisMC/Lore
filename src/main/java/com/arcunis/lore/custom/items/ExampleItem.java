package com.arcunis.lore.custom.items;

import com.arcunis.lore.custom.Item;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ExampleItem extends Item<ItemMeta> {

    public ExampleItem() throws RuntimeException {
        super(Material.STICK, ItemMeta.class, "example_item");
    }

    @Override
    protected void getMeta(ItemMeta meta) {
        meta.itemName(Component.text("Example Item"));
        meta.lore(List.of(
                Component.text("An example item to test the custom item system")
        ));
        meta.setCustomModelData(1);
    }

}
