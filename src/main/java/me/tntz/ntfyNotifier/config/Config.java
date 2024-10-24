package me.tntz.ntfyNotifier.config;

import me.tntz.ntfyNotifier.ntfy.MessageSettings;

import java.util.HashMap;

public class Config {
    public boolean enabled = false;
    public String serverURL = "http://localhost";
    public int port = 80;
    public String topic = "topic-here";

    public ConfigMessageSettings messageSettings = new ConfigMessageSettings();
}

