package me.tntz.ntfyNotifier.ntfy;

public class MessageSettings {
    public Priority priority = Priority.DEFAULT;
    public String[] tags = new String[]{};

    @SuppressWarnings("unused")
    public MessageSettings() {}
    @SuppressWarnings("unused")
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
