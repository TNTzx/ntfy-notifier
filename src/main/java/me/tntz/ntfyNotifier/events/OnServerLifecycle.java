package me.tntz.ntfyNotifier.events;

import me.tntz.ntfyNotifier.config.ConfigManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class OnServerLifecycle {
    public static void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(OnServerLifecycle::onServerStart);
        ServerLifecycleEvents.SERVER_STOPPED.register(OnServerLifecycle::onServerStop);
    }

    public static void onServerStart(MinecraftServer minecraftServer) {
        EventRegisterer.eventSendMessage(
                "SERVERSON",
                "Server started!",
                ConfigManager.getConfig().messageSettings.onServerStart
        );
    }

    public static void onServerStop(MinecraftServer minecraftServer) {
        EventRegisterer.eventSendMessage(
                "SERVERSOFF",
                "Server stopped!",
                ConfigManager.getConfig().messageSettings.onServerStop
        );
    }
}
