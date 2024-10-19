package com.arcunis.lore;

import com.arcunis.lore.commands.GiveCustomCommand;
import com.arcunis.lore.custom.Item;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register custom items
        for (Item<?> item : Bootstrapper.registry.getAllItems()) {
            item.register();
        }

        // Register events

        // Register commands
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            new GiveCustomCommand(commands);
        });

        // Register other

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
