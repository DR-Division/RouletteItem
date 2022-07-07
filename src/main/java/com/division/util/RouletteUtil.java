package com.division.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class RouletteUtil {

    public static boolean hasCoupon(ItemStack coupon, Player p) {
        if (coupon == null || coupon.getType() == Material.AIR) //쿠폰이 설정되지 않은경우
            return false;
        int amount = 0;
        for (ItemStack stack : p.getInventory().getContents()) {
            if (stack != null && isSimilar(coupon, stack)) {
                amount += stack.getAmount();
            }
        }
        return amount >= coupon.getAmount();
    }

    public static void removeCoupon(ItemStack coupon, Player p) {
        int amount = coupon.getAmount();
        for (ItemStack stack : p.getInventory().getContents()) {
            if (stack != null && isSimilar(coupon, stack)) {
                if (stack.getAmount() < amount) {
                    stack.setAmount(0);
                    break;
                }
                else {
                    stack.setAmount(stack.getAmount() - amount);
                }
            }
        }
    }

    public static void rollItem(Inventory inventory) {
        ItemStack firstItem = Objects.requireNonNull(inventory.getItem(9)).clone();
        for (int i = 10; i < 18; i++) {
            inventory.setItem(i - 1, inventory.getItem(i));
        }
        inventory.setItem(17, firstItem);
    }

    @SuppressWarnings("ConstantConditions") //itemMeta = 없을경우 새로 만들어서 반환 (NPE문제 없어보여서 경고 제거합니다)
    public static boolean isSimilar(ItemStack from, ItemStack to) {
        ItemMeta fromMeta, toMeta;
        String fromName, toName;
        List<String> fromLore, toLore;
        fromMeta = from.getItemMeta();
        toMeta = to.getItemMeta();
        fromName = fromMeta.getDisplayName();
        toName = toMeta.getDisplayName();
        fromLore = fromMeta.getLore();
        toLore = toMeta.getLore();
        boolean isSameType = from.getType() == to.getType();
        boolean isSameName = (fromName == null && toName == null) || ((fromName != null && toName != null) && fromName.equalsIgnoreCase(toName));
        boolean isSameLore = (fromLore == null && toLore == null) || ((fromLore != null && toLore != null) && fromLore.equals(toLore));

        return isSameType && isSameName && isSameLore;
    }
}
