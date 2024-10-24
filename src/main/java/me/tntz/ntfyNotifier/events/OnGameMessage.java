package me.tntz.ntfyNotifier.events;

import me.tntz.ntfyNotifier.config.ConfigManager;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;

public class OnGameMessage {
    public static void onInitialize() {
        ServerMessageEvents.GAME_MESSAGE.register(OnGameMessage::callback);
    }

    public static void callback(MinecraftServer server, Text text, boolean bool) {
        String rawText = text.getString();
        if (rawText.matches("^.+ (joined|left) the game$")) return;

        EventRegisterer.eventSendMessage(
                text.getString(),
                "A server message appeared!",
                ConfigManager.getConfig().messageSettings.onGameMessage
        );
    }
}
