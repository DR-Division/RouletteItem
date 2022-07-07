package com.division.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiTaskData {

    public static Map<UUID, Integer> taskID = new HashMap<>();

    public static int getTaskID(UUID uid) {
        return taskID.getOrDefault(uid, -1);
    }

    public static void setTaskID(UUID uid, int id) {
        taskID.put(uid, id);
    }
}
