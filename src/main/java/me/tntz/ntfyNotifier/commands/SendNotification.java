package me.tntz.ntfyNotifier.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import me.tntz.ntfyNotifier.ntfy.Connector;
import me.tntz.ntfyNotifier.ntfy.Priority;
import me.tntz.ntfyNotifier.ntfy.PriorityManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class SendNotification {
//    private static final String TITLE_ARG = "title";
//    private static final String MESSAGE_ARG = "customMessage";
//    private static final String TAGS_ARG = "tags";
//
//    public static void register() {
//
//    }
//
//    private static int command(CommandContext<ServerCommandSource> ctx, Priority priority) {
//        ctx.getSource().sendFeedback(() -> Text.literal("Sending..."), true);
//
//        String title = StringArgumentType.getString(ctx, TITLE_ARG);
//        String message = StringArgumentType.getString(ctx, MESSAGE_ARG);
//        String[] tags = StringArgumentType.getString(ctx, )
//        Connector.sendMessage(title, message, priority, );
//
//        return 0;
//    }
}
