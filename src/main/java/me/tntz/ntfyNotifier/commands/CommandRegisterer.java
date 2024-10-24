package me.tntz.ntfyNotifier.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.tntz.ntfyNotifier.NtfyNotifier;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;


public class CommandRegisterer {
    public static LiteralArgumentBuilder<ServerCommandSource> getRootLiteral(String commandName) {
        return literal(NtfyNotifier.MOD_ID + ":" + commandName);
    }

    public static boolean requiresOp(ServerCommandSource source) {
        return source.hasPermissionLevel(2);
    }

    public static void onInitialize() {
        MinPriority.onInitialize();
        SendNotification.onInitialize();
    }
}
