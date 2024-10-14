package com.arcunis.lore;

import com.arcunis.lore.custom.Registry;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Lore extends JavaPlugin {

    public static Registry registry;

    public Logger logger;

    @Override
    public void onLoad() {
        registry = new Registry(this);
        this.logger = getLogger();

    }

    @Override
    public void onEnable() {
        // Register events

        // Register custom items

        // Register commands

        // Register other

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
