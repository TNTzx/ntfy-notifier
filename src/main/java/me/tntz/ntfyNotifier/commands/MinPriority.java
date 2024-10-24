package me.tntz.ntfyNotifier.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.tntz.ntfyNotifier.commands.customArgs.PriorityArgument;
import me.tntz.ntfyNotifier.ntfy.Priority;
import me.tntz.ntfyNotifier.ntfy.PriorityManager;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.*;

public class MinPriority {

    public static void onInitialize() {
        CommandRegistrationCallback.EVENT.register(((commandDispatcher, commandRegistryAccess, registrationEnvironment) ->
                commandDispatcher.register(CommandRegisterer.getRootLiteral("minpriority")
                        .requires(CommandRegisterer::requiresOp)
                        .then(literal("set")
                                .then(PriorityArgument.arg
                                        .executes(MinPriority::set)
                                )
                        )
                        .then(literal("get")
                                .executes(MinPriority::get)
                        )
                )
        ));
    }


    public static int set(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        Priority priority = PriorityArgument.getArgValue(ctx);
        PriorityManager.setMinPriority(priority);
        ctx.getSource().sendFeedback(
                () -> Text.literal("Set minimum priority of Ntfy notifications to " + priority.POST_NAME + "."),
                true
        );
        return 0;
    }

    public static int get(CommandContext<ServerCommandSource> ctx) {
        ctx.getSource().sendFeedback(
                () -> Text.literal("Current minimum priority is " + PriorityManager.minPriority.POST_NAME + "."),
                true
        );

        return 0;
    }
}
