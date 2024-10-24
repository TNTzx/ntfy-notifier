package me.tntz.ntfyNotifier.ntfy;

public enum Priority {
    MAX("max", 5),
    HIGH("high", 4),
    DEFAULT("default", 3),
    LOW("low", 2),
    MIN("min", 1);

    public final String POST_NAME;
    public final int ID;
    Priority(String postName, int id) {
        this.POST_NAME = postName;
        this.ID = id;
    }
}
