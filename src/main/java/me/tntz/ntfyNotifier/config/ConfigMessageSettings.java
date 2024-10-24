package me.tntz.ntfyNotifier.config;

import me.tntz.ntfyNotifier.ntfy.MessageSettings;
import me.tntz.ntfyNotifier.ntfy.Priority;

public class ConfigMessageSettings {
    public MessageSettings onServerStart = new MessageSettings(Priority.MAX, new String[] {"white_check_mark"});
    public MessageSettings onServerStop = new MessageSettings(Priority.MAX, new String[] {"x"});

    public MessageSettings onChatMessage = new MessageSettings(Priority.LOW);
    public MessageSettings onGameMessage = new MessageSettings(Priority.DEFAULT, new String[] {"boom"});

    public MessageSettings onPlayerJoin = new MessageSettings(Priority.HIGH, new String[] {"hammer_and_pick"});
    public MessageSettings onPlayerDisconnect = new MessageSettings(Priority.HIGH, new String[] {"chopsticks"});
}
