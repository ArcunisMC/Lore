package com.arcunis.lore;

import com.arcunis.lore.custom.Registry;
import com.arcunis.lore.custom.enchantments.SoulboundEnchantment;
import com.arcunis.lore.custom.items.ExampleItem;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.event.WritableRegistry;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bootstrapper implements PluginBootstrap {

    public static Main plugin;
    public static String NAMESPACE = "lore";

    public static ComponentLogger logger;
    public static Registry registry;

    @Override
    public void bootstrap(BootstrapContext context) {

        // Get the logger and make it public
        logger = context.getLogger();

        // Initialize registry
        registry = new Registry();

        // Register custom items
        registry.registerItem(new ExampleItem());


        // Register custom enchantments
        context.getLifecycleManager().registerEventHandler(RegistryEvents.ENCHANTMENT.freeze().newHandler(event -> {
            WritableRegistry<Enchantment, EnchantmentRegistryEntry.Builder> enchantments = event.registry();

            // Put custom enchantments in the registry
            //registry.registerEnchantment(new ExampleEnchantment(event));
            registry.registerEnchantment(new SoulboundEnchantment(event));


            // Register the custom enchantments
            for (com.arcunis.lore.custom.Enchantment enchantment : registry.getAllEnchantments()) {
                enchantments.register(
                        TypedKey.create(
                                RegistryKey.ENCHANTMENT,
                                new NamespacedKey(
                                        NAMESPACE,
                                        enchantment.identifier
                                )
                        ),
                        builder -> enchantment.builder(builder)
                );
            }

        }));

    }

    @Override
    public JavaPlugin createPlugin(PluginProviderContext context) {
        plugin = new Main();
        return plugin;
    }

}
