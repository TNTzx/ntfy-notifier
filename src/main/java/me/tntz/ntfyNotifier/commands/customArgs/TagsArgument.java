package me.tntz.ntfyNotifier.commands.customArgs;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;

public class TagsArgument {
    public static String ARG_NAME = "tags";
    public static RequiredArgumentBuilder<ServerCommandSource, String> arg = argument(ARG_NAME, StringArgumentType.word())
            .suggests((ctx, builder) -> {
                builder.suggest("tag1,tag2,tag3,...,tagLast");
                return builder.buildFuture();
            });

    public static String[] getArgValue(CommandContext<ServerCommandSource> ctx) {
        String tags = ctx.getArgument(ARG_NAME, String.class);
        return tags.split(",");
    }
}
