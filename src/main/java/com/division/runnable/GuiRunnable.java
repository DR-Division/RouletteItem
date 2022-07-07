package com.division.runnable;

import com.division.data.RouletteData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GuiRunnable implements Runnable {

    private int tick;
    private int count;
    private final UUID uuid;
    private final int maxCount;
    private final int lastSlot;
    private final int slowTime;
    private final int addTick;
    private final int slotInterval;
    private final List<Integer> slowList;


    public GuiRunnable(UUID p, RouletteData data) {
        this.tick = 0; //1틱 단위 runnable
        this.count = data.getFirstTick();
        this.uuid = p;
        this.maxCount = data.getMaxTick();
        this.lastSlot = data.getItemSlot();
        this.slowTime = data.getSlowNumber();
        this.addTick = data.getAddTick();
        this.slotInterval = data.getSlowInterval();
        slowList = new ArrayList<>();
    }

    @Override
    public void run() {
        if (tick >= maxCount) {
            giveItem();
        }
        if (count >= tick) {

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

    }
}
