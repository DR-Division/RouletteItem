package com.division.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RouletteUtil {

    public static boolean hasCoupon(ItemStack coupon, Player p) {
        if (coupon == null || coupon.getType() == Material.AIR) //쿠폰이 설정되지 않은경우
            return false;
        return p.getInventory().contains(coupon);
    }

    public static void removeCoupon(ItemStack coupon, Player p) {
        p.getInventory().remove(coupon);
    }
}
