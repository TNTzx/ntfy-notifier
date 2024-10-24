package me.tntz.ntfyNotifier.config;

public class Config {
    public boolean enabled = false;
    public String serverURL = "http://localhost";
    public int port = 80;
    public String topic = "topic-here";

    public ConfigMessageSettings messageSettings = new ConfigMessageSettings();
}

