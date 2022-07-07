package com.division.runnable;

import com.division.data.GuiTaskData;
import com.division.data.RouletteData;
import com.division.event.RouletteItemGainEvent;
import com.division.util.RouletteUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GuiRunnable implements Runnable {

    private int tick;
    private int rouletteTick;
    private int currentAddTick;
    private final UUID uuid;
    private final int maxCount;
    private final int lastSlot;
    private final int slowTime;
    private final int addTick;
    private final int slotInterval;
    private final Inventory inventory;
    private final List<Integer> slowList;


    public GuiRunnable(UUID p, RouletteData data, Inventory rouletteInventory) {
        this.tick = 0; //1틱 단위 runnable
        this.rouletteTick = data.getFirstTick();
        this.currentAddTick = data.getFirstTick(); //변화하는 수치 (addTick에 영향받음)
        this.uuid = p;
        this.maxCount = data.getMaxTick();
        this.lastSlot = data.getItemSlot();
        this.slowTime = data.getSlowNumber();
        this.addTick = data.getAddTick();
        this.inventory = rouletteInventory;
        this.slotInterval = data.getSlowInterval();
        slowList = new ArrayList<>();
    }

    @Override
    public void run() {
        if (tick >= maxCount) {
            giveItem();
        }
        else if (slowList.contains(tick))
            currentAddTick += addTick; //느려지는 틱 주기 넘어서면 느려짐
        else if (rouletteTick <= tick) {
            Player p = Bukkit.getPlayer(uuid);
            rouletteTick += currentAddTick; //다음에 움직일 틱 설정
            RouletteUtil.rollItem(inventory);
            if (p != null)
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.5f, 3.0f);
        }

        tick++;
    }

    //속도가 느려지는 구간 리스트 초기화
    public void initList() {
        for (int i = 1; i <= slowTime; i++) {
            slowList.add(i * slotInterval);
        }
    }

    public void giveItem() {
        Player p = Bukkit.getPlayer(uuid);
        Bukkit.getScheduler().cancelTask(GuiTaskData.getTaskID(uuid));
        if (p != null) {
            Bukkit.getPluginManager().callEvent(new RouletteItemGainEvent(p, inventory.getItem(lastSlot)));
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1.0f);
            p.getInventory().addItem(inventory.getItem(lastSlot));
            p.closeInventory();
        }
    }
}
