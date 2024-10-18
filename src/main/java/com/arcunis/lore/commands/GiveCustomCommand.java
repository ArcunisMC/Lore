package com.arcunis.lore.commands;

import com.arcunis.lore.Bootstrapper;
import com.arcunis.lore.custom.Item;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GiveCustomCommand {

    public GiveCustomCommand(Commands commands) {
        commands.register(
                Commands.literal("givecustom")
                        .requires(source -> source.getSender().hasPermission("lore.commands.givecustom"))
                        .then(
                                Commands.argument("targets", ArgumentTypes.players())
                                        .then(
                                                Commands.argument("item", StringArgumentType.string())
                                                        .suggests(this::itemSuggestor)
                                                        .executes(this::giveSingle)
                                                        .then(
                                                                Commands.argument("amount", IntegerArgumentType.integer(0))
                                                                        .executes(this::give)
                                                        )
                                        )
                        )
                        .build(),
                "Gives a custom item to one or more players"
        );
    }

    private CompletableFuture<Suggestions> itemSuggestor(CommandContext<CommandSourceStack> ctx, SuggestionsBuilder builder) {
        for (String identifier : Bootstrapper.registry.getAllItemIdentifiers()) builder.suggest(identifier);
        return builder.buildFuture();
    }

    private int giveSingle(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {

        List<Player> players = ctx.getArgument("targets", PlayerSelectorArgumentResolver.class).resolve(ctx.getSource());
        String identifier = StringArgumentType.getString(ctx, "item");

        Item<?> item = Bootstrapper.registry.getItem(identifier);

        if (item == null) {
            ctx.getSource().getSender().sendMessage(
                    Component.text("Unknown item '%s'".formatted(identifier))
                            .color(NamedTextColor.RED)
            );
            return Command.SINGLE_SUCCESS;
        }

        for (Player player : players) {
            player.getInventory().addItem(item.getItemStack());
        }

        return Command.SINGLE_SUCCESS;
    }

    private int give(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {

        List<Player> players = ctx.getArgument("targets", PlayerSelectorArgumentResolver.class).resolve(ctx.getSource());
        String identifier = StringArgumentType.getString(ctx, "item");
        int amount = IntegerArgumentType.getInteger(ctx, "amount");

        Item<?> item = Bootstrapper.registry.getItem(identifier);

        if (item == null) {
            ctx.getSource().getSender().sendMessage(
                    Component.text("Unknown item '%s'".formatted(identifier))
                            .color(NamedTextColor.RED)
            );
            return Command.SINGLE_SUCCESS;
        }

        for (Player player : players) {
            player.getInventory().addItem(item.getItemStack().asQuantity(amount));
        }

        return Command.SINGLE_SUCCESS;
    }

}
