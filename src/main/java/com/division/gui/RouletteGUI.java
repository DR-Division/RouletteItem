package com.division.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RouletteGUI {

    private final ItemStack[] guiItems;

    public RouletteGUI(ItemStack[] data) {
        this.guiItems = data;
    }

    public void openGUI(Player p, boolean isSettingMenu) {
        Inventory inventory;
        if (isSettingMenu)
            inventory = Bukkit.createInventory(null, 27, "§0룰렛 설정");
        else
            inventory = Bukkit.createInventory(null, 27, "§0아이템 룰렛");
        for (int i = 0; i < guiItems.length; i++) {
            inventory.setItem(i, guiItems[i]);
        }
        p.openInventory(inventory);

    }
}
