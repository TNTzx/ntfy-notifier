package me.tntz.ntfyNotifier.events;

import me.tntz.ntfyNotifier.NtfyNotifier;
import me.tntz.ntfyNotifier.ntfy.Connector;
import me.tntz.ntfyNotifier.ntfy.MessageSettings;

import java.io.IOException;
import java.net.URISyntaxException;


public class EventRegisterer {
    public static void eventSendMessage(String title, String description, MessageSettings messageSettings) {
        try {
            Connector.sendMessage(title, description, messageSettings);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            NtfyNotifier.LOGGER.error("Unexpected error while trying to send message with title \"{}\": {}", title, e);
        }
    }

    public static void onInitialize() {
        OnServerLifecycle.onInitialize();
        OnPlayerFlow.onInitialize();
        OnChatMessage.onInitialize();
        OnGameMessage.onInitialize();
    }
}
