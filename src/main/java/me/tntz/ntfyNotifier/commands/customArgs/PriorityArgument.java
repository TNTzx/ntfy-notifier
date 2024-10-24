package me.tntz.ntfyNotifier.commands.customArgs;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.tntz.ntfyNotifier.ntfy.Priority;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class PriorityArgument {
    public static String ARG_NAME = "priority";
    public static RequiredArgumentBuilder<ServerCommandSource, String> arg = argument(ARG_NAME, StringArgumentType.word())
            .suggests((ctx, builder) -> {
                for (Priority priority : Priority.values()) {
                    builder.suggest(priority.POST_NAME);
                }

                return builder.buildFuture();
            });

    public static Priority getArgValue(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        String priority = ctx.getArgument(ARG_NAME, String.class);
        try {
            return Priority.fromPostName(priority);
        } catch (NoSuchElementException e) {
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.literalIncorrect().create(
                    "\"" + priority + "\" is not a proper priority. " +
                    "Good priority names are: " + String.join(", ", Arrays.stream(Priority.values()).map(prio -> prio.POST_NAME).toArray(String[]::new))
            );
        }
    }
}
