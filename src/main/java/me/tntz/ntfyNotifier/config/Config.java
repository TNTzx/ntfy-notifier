package me.tntz.ntfyNotifier.config;

import me.tntz.ntfyNotifier.ntfy.Priority;

public class Config {
    public boolean enabled = false;
    public String serverURL = "http://localhost";
    public int port = 80;
    public String topic = "topic-here";
    public Priority initialMinPriority = Priority.MIN;

    public ConfigMessageSettings messageSettings = new ConfigMessageSettings();
}

