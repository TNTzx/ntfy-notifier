package me.tntz.ntfyNotifier.events;

import me.tntz.ntfyNotifier.config.ConfigManager;
import me.tntz.ntfyNotifier.ntfy.MessageSettings;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.text.Text;

public class OnPlayerFlow {
    public static void onInitialize() {
        ServerPlayConnectionEvents.JOIN.register(OnPlayerFlow::onPlayerJoin);
        ServerPlayConnectionEvents.DISCONNECT.register(OnPlayerFlow::onPlayerDisconnect);
    }

    private static void sendPlayerFlowNotif(
            ServerPlayNetworkHandler handler, MinecraftServer server,
            String titleAction, String descriptionAction, int cpcOffset,
            MessageSettings messageSettings
    ) {
        String username = handler.getPlayer().getName().getString();
        EventRegisterer.eventSendMessage(
                username + " " + titleAction + "! [" + (server.getCurrentPlayerCount() + cpcOffset) + "/" + server.getMaxPlayerCount() + "]",
                username + " just " + descriptionAction + " the server.",
                messageSettings
        );
    }

    public static void onPlayerJoin(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        sendPlayerFlowNotif(handler, server, "joined", "joined", 1, ConfigManager.getConfig().messageSettings.onPlayerJoin);
    }

    public static void onPlayerDisconnect(ServerPlayNetworkHandler handler, MinecraftServer server) {
        sendPlayerFlowNotif(handler, server, "disconnected", "disconnected from", -1, ConfigManager.getConfig().messageSettings.onPlayerDisconnect);
    }
}
