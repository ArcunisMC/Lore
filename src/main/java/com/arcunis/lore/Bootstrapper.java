package com.arcunis.lore;

import com.arcunis.lore.custom.Registry;
import com.arcunis.lore.custom.enchantments.SoulboundEnchantment;
import com.arcunis.lore.custom.items.ExampleItem;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.event.WritableRegistry;
import io.papermc.paper.registry.tag.TagKey;
import io.papermc.paper.tag.PostFlattenTagRegistrar;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

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

        // Get the lifecycle manager
        LifecycleEventManager<BootstrapContext> manager = context.getLifecycleManager();

        // Register custom enchantments

        Map<io.papermc.paper.registry.tag.TagKey<Enchantment>, Set<String>> tags = new HashMap<>();

        manager.registerEventHandler(RegistryEvents.ENCHANTMENT.freeze().newHandler(event -> {
            WritableRegistry<Enchantment, EnchantmentRegistryEntry.Builder> enchantments = event.registry();

            // Put custom enchantments in the registry
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
                for (TagKey<Enchantment> tag : enchantment.tags) {
                    if (!tags.containsKey(tag)) tags.put(tag, new HashSet<>());
                    tags.get(tag).add(enchantment.identifier);
                }
            }

        }));

        // Add enchantments to tags
        manager.registerEventHandler(LifecycleEvents.TAGS.postFlatten(RegistryKey.ENCHANTMENT), event -> {
            final PostFlattenTagRegistrar<Enchantment> registrar = event.registrar();
            for (Map.Entry<TagKey<Enchantment>, Set<String>> entry : tags.entrySet()) {
                registrar.addToTag(
                        entry.getKey(),
                        entry.getValue().stream().map(identifier ->
                                TypedKey.create(
                                        RegistryKey.ENCHANTMENT,
                                        Key.key(new NamespacedKey(NAMESPACE, identifier).asString())
                                )
                        ).toList()
                );
            }
        });
    }

    @Override
    public JavaPlugin createPlugin(PluginProviderContext context) {
        plugin = new Main();
        return plugin;
    }

}
