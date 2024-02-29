package com.arcunis.lore;

//import com.arcunis.core.Core;
//import com.arcunis.jdb.JDB;
import com.arcunis.lore.customitems.CustomItems;
import com.arcunis.lore.events.OnUnequipEvent;
import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Main extends JavaPlugin implements Listener {

//    public static Core core;
//    public static JDB jdb;

    @Override
    public void onEnable() {
//        core = (Core) Bukkit.getPluginManager().getPlugin("Core");
//        try {
//            jdb = new JDB(getDataFolder());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        Bukkit.getPluginManager().registerEvents(this, this);

        new CustomItems(this);
    }

    @Override
    public void onDisable() {
//        try {
//            jdb.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @EventHandler
    public void onArmorChange(PlayerArmorChangeEvent event) {
        ItemStack oldItem = event.getOldItem();
        if (oldItem.getType() != Material.AIR) {
            new OnUnequipEvent(event.getPlayer(), oldItem).callEvent();
        }

        ItemStack newItem = event.getNewItem();
        if (newItem.getType() != Material.AIR) {
            new OnUnequipEvent(event.getPlayer(), newItem).callEvent();
        }
    }

}
