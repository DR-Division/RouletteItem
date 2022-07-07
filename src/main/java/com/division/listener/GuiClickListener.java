package com.division.listener;

import com.division.data.GuiTaskData;
import com.division.data.RouletteData;
import com.division.runnable.GuiRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GuiClickListener implements Listener {

    private final RouletteData data;
    private final JavaPlugin Plugin;

    public GuiClickListener(RouletteData data, JavaPlugin Plugin) {
        this.data = data;
        this.Plugin = Plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().contains("아이템 룰렛")) {
            event.setCancelled(true); //룰렛 자체 아이템 움직임 방지
            int slot = event.getRawSlot();
            Player p = (Player) event.getWhoClicked();
            ItemStack stack = event.getCurrentItem();
            if (stack != null && stack.getType() != Material.AIR && slot == data.getClickSlot()) {
                //시작버튼을 클릭한경우
                GuiRunnable runnable = new GuiRunnable(p.getUniqueId(), data);
                runnable.initList();
                event.getInventory().setItem(slot, new ItemStack(Material.BARRIER));
                GuiTaskData.setTaskID(p.getUniqueId(), Bukkit.getScheduler().runTaskTimer(Plugin, runnable, 0L, 1L).getTaskId());
            }
        }
    }
}
