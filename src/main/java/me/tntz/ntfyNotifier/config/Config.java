package me.tntz.ntfyNotifier.config;

import java.util.HashMap;

public class Config {
    public boolean enabled = false;
    public String serverURL = "http://localhost";
    public int port = 80;
    public String appToken = "appToken here";

    public HashMap<String, Object> getData() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("enabled", enabled);
        data.put("serverURL", serverURL);
        data.put("port", port);
        data.put("appToken", appToken);

        return data;
    }
}

