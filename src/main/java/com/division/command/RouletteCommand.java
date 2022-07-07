package com.division.command;

import com.division.data.RouletteData;
import com.division.file.GuiConfig;
import com.division.gui.RouletteGUI;
import com.division.util.RouletteUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class RouletteCommand implements CommandExecutor, TabCompleter {

    private final List<String> commandList = Arrays.asList("리로드", "쿠폰설정", "정보", "gui", "저장", "활성화", "비활성화", "열기");
    private final GuiConfig config;
    private final RouletteData data;

    public RouletteCommand(GuiConfig config, RouletteData data) {
        this.config = config;
        this.data = data;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (strings.length >= 1 && commandList.contains(strings[0]) && p.isOp()) {
                switch (strings[0]) {
                    case "리로드":
                        boolean reloadGUI = strings.length >= 2 && strings[1].equalsIgnoreCase("아이템"); //룰렛 리로드 아이템
                        config.loadConfig();
                        data.loadData(reloadGUI);
                        p.sendMessage("리로드 완료 [GUI 리로드 여부 : §b" + reloadGUI + "§f]");
                        break;
                    case "쿠폰설정":
                        ItemStack stack = p.getInventory().getItemInMainHand();
                        if (stack.getType() == Material.AIR)
                            p.sendMessage("아이템을 들어주세요");
                        else {
                            data.setCoupon(stack);
                            p.sendMessage("아이템 설정완료 : [아이템 : §b" + stack.getType() + "§f]");
                        }
                        break;
                    case "정보":
                        sendInformation(p);
                        break;
                    case "gui":
                        new RouletteGUI(data.getGuiData()).openGUI(p, true);
                        break;
                    case "저장":
                        data.saveData();
                        config.saveConfig();
                        p.sendMessage("저장되었습니다.");
                        break;
                    case "활성화":
                        if (data.isActive())
                            p.sendMessage("현재 활성화된 상태입니다.") ;
                        else {
                            p.sendMessage("룰렛이 활성화 되었습니다.");
                            data.setActive(true);
                        }
                        break;
                    case "비활성화":
                        if (!data.isActive())
                            p.sendMessage("현재 비활성화된 상태입니다.") ;
                        else {
                            p.sendMessage("룰렛이 비활성화 되었습니다.");
                            data.setActive(false);
                        }
                        break;
                    case "열기":
                        openRoulette(p);
                        break;
                    default:
                        sendCommandUsage(p);
                        break;
                }
            }
            else if (p.isOp())
                sendCommandUsage(p);
            else
                openRoulette(p);
                return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 1 && commandSender.isOp())
            return commandList;
        return null;
    }

    public void openRoulette(Player p) {
        if (data.isActive()) {
            ItemStack coupon = data.getCoupon();
            if (RouletteUtil.hasCoupon(coupon, p)) {
                RouletteUtil.removeCoupon(coupon, p);
                new RouletteGUI(data.getGuiData()).openGUI(p, false);
            }
            else
                p.sendMessage("쿠폰을 보유하고 있지 않습니다.");
        }
        else
            p.sendMessage("현재 아이템 룰렛이 비활성화 되어있습니다.");
    }

    public void sendInformation(Player p) {
        p.sendMessage(" ");
        p.sendMessage("활성화 상태 : " + data.isActive());
        p.sendMessage("작동시간 : " + data.getMaxTick() + "틱");
        p.sendMessage("최초 룰렛 작동 간격 : " + data.getFirstTick() + "틱");
        p.sendMessage("최종 아이템 슬롯 : " + (data.getItemSlot() + 1) + "번째");
        p.sendMessage("룰렛 작동 아이템 슬롯 : " + (data.getClickSlot() + 1) + "번째");
        p.sendMessage("감속 횟수 : " + data.getSlowNumber() + "회");
        p.sendMessage("감속당 느려지는 틱 수치 : " + data.getAddTick() + "틱");
        p.sendMessage("감속 간격 : " + data.getSlowInterval() + "틱");
        p.sendMessage("쿠폰 아이템 : " + (data.getCoupon().getType() == Material.AIR ? "설정되지 않음" : data.getCoupon().getType()));
        p.sendMessage(" ");
    }

    public void sendCommandUsage(Player p) {
        p.sendMessage("§f/룰렛 [정보/리로드 [아이템]/저장/쿠폰설정/gui/활성화/비활성화/열기");
    }


}
