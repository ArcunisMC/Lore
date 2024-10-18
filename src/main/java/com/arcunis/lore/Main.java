package com.arcunis.lore;

import com.arcunis.lore.commands.GiveCustomCommand;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
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
