package me.tntz.ntfyNotifier.ntfy;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.github.mizosoft.methanol.Methanol;
import com.github.mizosoft.methanol.MutableRequest;

import me.tntz.ntfyNotifier.NtfyNotifier;
import me.tntz.ntfyNotifier.config.*;


public class Connector {
    private static final Methanol client = Methanol.create();

    private static URI getMessageUri() throws IOException, URISyntaxException {
        Config config = ConfigManager.getConfig();
        URI oldURI = new URI(config.serverURL);

        return new URI(
                oldURI.getScheme(),
                oldURI.getUserInfo(),
                oldURI.getHost(),
                config.port,
                oldURI.getPath() + "/" + config.topic,
                oldURI.getQuery(),
                oldURI.getFragment()
        );
    }


    private static boolean isEnabled() throws IOException {
        return ConfigManager.getConfig().enabled;
    }

    @SuppressWarnings("unused")
    public static void sendMessage(String title, String message) throws IOException, URISyntaxException, InterruptedException {
        sendMessage(title, message, Priority.DEFAULT);
    }
    public static void sendMessage(String title, String message, Priority priority) throws IOException, URISyntaxException, InterruptedException {
        sendMessage(title, message, priority, new String[]{});
    }
    public static void sendMessage(String title, String message, MessageSettings messageSettings) throws IOException, URISyntaxException, InterruptedException {
        sendMessage(title, message, messageSettings.priority, messageSettings.tags);
    }
    public static void sendMessage(String title, String message, Priority priority, String[] tags) throws IOException, URISyntaxException, InterruptedException {
        if (!isEnabled()) {
            NtfyNotifier.LOGGER.warn(
                    "Message with title \"{}\" cannot be sent as Ntfy Notifier is disabled. " +
                            "Open the config file at {} then set \"enabled\" to true.",
                    title, ConfigManager.CONFIG_LOCATION
            );
            return;
        }
        if (!PriorityManager.isQualified(priority)) {
            NtfyNotifier.LOGGER.warn(
                    "Message with title \"{}\" and priority {} cannot be sent as minimum priority level is set to {}. " +
                            "Use {} to set a higher priority level.",
                    title, priority.POST_NAME, PriorityManager.minPriority.POST_NAME, "TODO command" // TODO command
            );
            return;
        }

        var request = MutableRequest.POST(getMessageUri(), HttpRequest.BodyPublishers.ofString(message))
                .header("Title", title)
                .header("Priority", priority.POST_NAME)
                .header("Tags", String.join(",", tags))
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        NtfyNotifier.LOGGER.info("Sent message of priority {}: {} | {} || {}", priority.name(), title, message, response.body());
    }
}
