package com.division.data;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class RouletteData {

    private final YamlConfiguration config;
    private boolean isActive;
    private int maxTick;
    private int firstTick;
    private int itemSlot;
    private int clickSlot;
    private int slowNumber;
    private int addTick;
    private int slowInterval;
    private ItemStack coupon;
    private final ItemStack[] guiData;

    public RouletteData(YamlConfiguration config) {
        guiData = new ItemStack[27];
        this.config = config;
    }

    public void loadData(boolean loadGUI) {
        maxTick = config.getInt("max-tick", 180);
        firstTick = config.getInt("first-tick", 2);
        itemSlot = config.getInt("get-item-slot", 12);
        clickSlot = config.getInt("click-item-slot", 4);
        slowNumber = config.getInt("slow.number", 5);
        addTick = config.getInt("slow.add-tick-per-interval", 2);
        slowInterval = config.getInt("slow.slow-interval", 40);
        if (loadGUI) {
            coupon = config.getItemStack("items.coupon", new ItemStack(Material.AIR));
            for (int i = 0; i < guiData.length; i++) {
                guiData[i] = config.getItemStack("items.gui." + i, new ItemStack(Material.AIR));
            }
        }
    }

    public void saveData() {
        config.set("items.coupon", coupon);
        for (int i = 0; i < guiData.length; i++) {
            config.set("items.gui." + i, guiData[i]);
        }
    }

    public int getMaxTick() {
        return maxTick;
    }

    public int getFirstTick() {
        return firstTick;
    }

    public int getItemSlot() {
        return itemSlot;
    }

    public int getClickSlot() {
        return clickSlot;
    }

    public int getSlowNumber() {
        return slowNumber;
    }

    public int getAddTick() {
        return addTick;
    }

    public int getSlowInterval() {
        return slowInterval;
    }

    public ItemStack getCoupon() {
        return coupon;
    }

    public void setCoupon(ItemStack coupon) {
        this.coupon = coupon;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public ItemStack[] getGuiData() {
        return guiData;
    }


}
