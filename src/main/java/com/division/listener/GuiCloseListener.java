package com.division.listener;

import com.division.data.GuiTaskData;
import com.division.data.RouletteData;
import org.bukkit.Bukkit;
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
        String title = event.getView().getTitle();
        if (title.contains("룰렛 설정")) {
            for (int i = 0; i < 27; i++) {
                ItemStack stack = event.getInventory().getItem(i);
                if (stack != null && stack.getType() != Material.AIR)
                        data.setGuiDataByIndex(stack, i);
                else
                    data.setGuiDataByIndex(new ItemStack(Material.AIR), i);
            }
        }
        else if (title.contains("아이템 룰렛")) {
            Bukkit.getScheduler().cancelTask(GuiTaskData.getTaskID(event.getPlayer().getUniqueId()));
        }
    }
}
