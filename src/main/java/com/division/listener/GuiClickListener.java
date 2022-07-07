package com.division.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().contains("아이템 룰렛")) {
            event.setCancelled(true); //룰렛 자체 아이템 움직임 방지

        }
    }
}
