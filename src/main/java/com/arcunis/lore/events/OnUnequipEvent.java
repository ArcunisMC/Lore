package com.arcunis.lore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import net.kyori.adventure.text.Component;

public class OnUnequipEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private Player player;
    private ItemStack item;

    public OnUnequipEvent(Player player, ItemStack item) {
        this.player = player;
        this.item = item;
    }

    public Player getPlayer() {
        return player;
    }
    public ItemStack getItem() {
        return item;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

}