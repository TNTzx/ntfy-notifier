package me.tntz.ntfyNotifier.events;

import me.tntz.ntfyNotifier.config.ConfigManager;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.network.ServerPlayerEntity;

public class OnChatMessage {
    public static void onInitialize() {
        ServerMessageEvents.CHAT_MESSAGE.register(OnChatMessage::callback);
    }

    public static void callback(SignedMessage signedMessage, ServerPlayerEntity serverPlayerEntity, MessageType.Parameters parameters) {
        EventRegisterer.eventSendMessage(
                serverPlayerEntity.getName().getString() + " messaged in the server",
                signedMessage.getSignedContent(),
                ConfigManager.getConfig().messageSettings.onChatMessage
        );
    }
}
