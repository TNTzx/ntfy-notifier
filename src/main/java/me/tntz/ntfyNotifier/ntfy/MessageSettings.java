package me.tntz.ntfyNotifier.ntfy;

import java.util.HashMap;

public class MessageSettings {
    public Priority priority = Priority.DEFAULT;
    public String[] tags = new String[]{};

    public MessageSettings() {}
    public MessageSettings(String[] tags) {
        this.tags = tags;
    }
    public MessageSettings(Priority priority) {
        this.priority = priority;
    }
    public MessageSettings(Priority priority, String[] tags) {
        this.priority = priority;
        this.tags = tags;
    }
}
