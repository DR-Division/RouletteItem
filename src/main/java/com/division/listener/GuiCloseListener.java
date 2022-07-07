package com.division.listener;

import com.division.data.RouletteData;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class GuiCloseListener implements Listener {

    private final RouletteData data;

    public GuiCloseListener(RouletteData data) {
        this.data = data;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().contains("룰렛 설정")) {
            for (int i = 0; i < 27; i++) {
                ItemStack stack = event.getInventory().getItem(i);
                if (stack != null && stack.getType() != Material.AIR)
                        data.setGuiDataByIndex(stack, i);
            }
        }
    }
}
