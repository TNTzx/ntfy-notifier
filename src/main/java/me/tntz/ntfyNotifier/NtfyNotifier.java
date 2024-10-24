package me.tntz.ntfyNotifier;

import me.tntz.ntfyNotifier.commands.CommandRegisterer;
import me.tntz.ntfyNotifier.events.EventRegisterer;
import me.tntz.ntfyNotifier.ntfy.Connector;
import me.tntz.ntfyNotifier.ntfy.Priority;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

public class NtfyNotifier implements ModInitializer {

    public static final String MOD_ID = "ntfy-notifier";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("=== Ntfy Notifier Initialization ===");

        LOGGER.info("Sending initialization message...");
        try {
            Connector.sendMessage("Ntfy Notifier Initializing", "The server is initializing.", Priority.MIN, new String[]{"warning"});
        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("Message sent successfully.");

        LOGGER.info("Initializing commands...");
        CommandRegisterer.onInitialize();

        LOGGER.info("Initializing events...");
        EventRegisterer.onInitialize();

        LOGGER.info("=== Ntfy Notifier Initialized successfully! ===");
    }
}
