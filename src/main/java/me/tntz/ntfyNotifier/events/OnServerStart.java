package me.tntz.ntfyNotifier.events;

import me.tntz.ntfyNotifier.config.ConfigManager;
import me.tntz.ntfyNotifier.ntfy.Connector;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.net.URISyntaxException;

public class OnServerStart {
    public static void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(OnServerStart::callback);
    }

    public static void callback(MinecraftServer minecraftServer) {
        EventRegisterer.eventSendMessage(
                "SERVERSON",
                "Server started!",
                ConfigManager.getConfig().messageSettings.onServerStart
        );
    }
}
