package me.tntz.ntfyNotifier.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.tntz.ntfyNotifier.commands.customArgs.PriorityArgument;
import me.tntz.ntfyNotifier.commands.customArgs.TagsArgument;
import me.tntz.ntfyNotifier.ntfy.Connector;
import me.tntz.ntfyNotifier.ntfy.Priority;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.io.IOException;
import java.net.URISyntaxException;

import static net.minecraft.server.command.CommandManager.*;

public class SendNotification {
    private static final String TITLE_ARG = "title";
    private static final String MESSAGE_ARG = "customMessage";

    public static void onInitialize() {
        CommandRegistrationCallback.EVENT.register(((commandDispatcher, commandRegistryAccess, registrationEnvironment) ->
                commandDispatcher.register(CommandRegisterer.getRootLiteral("send")
                        .requires(CommandRegisterer::requiresOp)
                        .then(argument(TITLE_ARG, StringArgumentType.string())
                                .then(argument(MESSAGE_ARG, StringArgumentType.string())
                                        .executes(SendNotification::executeRequiredArgs)
                                        .then(PriorityArgument.arg
                                                .executes(SendNotification::executeWithPriority)
                                                .then(TagsArgument.arg
                                                        .executes(SendNotification::executeWithAll))
                                        )
                                )
                        )
                )
        ));
    }

    private static String parseTitle(CommandContext<ServerCommandSource> ctx) {
        return StringArgumentType.getString(ctx, TITLE_ARG);
    }
    private static String parseMessage(CommandContext<ServerCommandSource> ctx) {
        return StringArgumentType.getString(ctx, TITLE_ARG);
    }
    private static Priority parsePriority(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        return PriorityArgument.getArgValue(ctx);
    }
    private static String[] parseTags(CommandContext<ServerCommandSource> ctx) {
        return TagsArgument.getArgValue(ctx);
    }


    private static int executeRequiredArgs(CommandContext<ServerCommandSource> ctx) {
        return executeCommand(ctx, parseTitle(ctx), parseMessage(ctx), Priority.DEFAULT, new String[]{});
    }
    private static int executeWithPriority(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        return executeCommand(ctx, parseTitle(ctx), parseMessage(ctx), parsePriority(ctx), new String[]{});
    }
    private static int executeWithAll(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        return executeCommand(ctx, parseTitle(ctx), parseMessage(ctx), parsePriority(ctx), parseTags(ctx));
    }


    private static int executeCommand(CommandContext<ServerCommandSource> ctx, String title, String message, Priority priority, String[] tags) {
        ctx.getSource().sendFeedback(() -> Text.literal("Sending..."), true);
        try {
            Connector.sendMessage(title, message, priority, tags);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        ctx.getSource().sendFeedback(() -> Text.literal("Sent notification!"), true);

        return 0;
    }
}
