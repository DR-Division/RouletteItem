package com.division.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class RouletteItemGainEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final ItemStack item;
    private final Player p;

    public RouletteItemGainEvent(Player p, ItemStack stack) {
        this.p = p;
        this.item = stack;
    }

    public Player getPlayer() {
        return p;
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
