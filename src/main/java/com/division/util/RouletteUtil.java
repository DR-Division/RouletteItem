package com.division.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class RouletteUtil {

    public static boolean hasCoupon(ItemStack coupon, Player p) {
        if (coupon == null || coupon.getType() == Material.AIR) //쿠폰이 설정되지 않은경우
            return false;
        return p.getInventory().contains(coupon);
    }

    public static void removeCoupon(ItemStack coupon, Player p) {
        int amount = coupon.getAmount();
        for (ItemStack stack : p.getInventory().getContents()) {
            if (stack != null && stack.isSimilar(coupon)) {
                if (stack.getAmount() >= amount) {
                    if (stack.getAmount() > amount)
                        stack.setAmount(stack.getAmount() - amount);
                    else
                        p.getInventory().remove(stack);
                    break;
                }
                else
                    p.getInventory().remove(stack);
            }
        }
    }

    public static void rollItem(Inventory inventory) {
        ItemStack firstItem = Objects.requireNonNull(inventory.getItem(9)).clone();
        for (int i = 10; i < 18; i++) {
            inventory.setItem(i-1, inventory.getItem(i));
        }
        inventory.setItem(17, firstItem);
    }
}
