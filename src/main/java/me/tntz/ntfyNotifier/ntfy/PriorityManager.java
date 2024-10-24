package me.tntz.ntfyNotifier.ntfy;

public class PriorityManager {
    public static Priority minPriority = Priority.MIN;

    public static void setMinPriority(Priority minPriorityLevel) {
        minPriority = minPriorityLevel;
    }

    public static boolean isQualified(Priority priorityLevel) {
        return priorityLevel.ID >= minPriority.ID;
    }
}