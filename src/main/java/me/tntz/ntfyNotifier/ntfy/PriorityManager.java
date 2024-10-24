package me.tntz.ntfyNotifier.ntfy;

import me.tntz.ntfyNotifier.config.ConfigManager;

public class PriorityManager {
    public static Priority minPriority = ConfigManager.getConfig().initialMinPriority;

    public static void setMinPriority(Priority minPriorityLevel) {
        minPriority = minPriorityLevel;
    }

    public static boolean isQualified(Priority priorityLevel) {
        return priorityLevel.ID >= minPriority.ID;
    }
}