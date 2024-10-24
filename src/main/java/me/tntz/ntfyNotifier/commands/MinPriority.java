package me.tntz.ntfyNotifier.commands;

import com.mojang.brigadier.context.CommandContext;
import me.tntz.ntfyNotifier.commands.arguments.PriorityArgumentType;
import me.tntz.ntfyNotifier.ntfy.Priority;
import me.tntz.ntfyNotifier.ntfy.PriorityManager;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.*;

public class MinPriority {
    private static final String PRIORITY_ARG = "priority";

    public static void onInitialize() {
        CommandRegistrationCallback.EVENT.register(((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            commandDispatcher.register(CommandRegisterer.getRootLiteral("minpriority")
                    .requires(CommandRegisterer::requiresOp)
                    .then(literal("set")
                            .then(argument(PRIORITY_ARG, PriorityArgumentType.priorityType())
                                    .executes(MinPriority::set)
                            )
                    )
                    .then(literal("get")
                            .executes(MinPriority::get)
                    )
            );
        }));
    }


    public static int set(CommandContext<ServerCommandSource> ctx) {
        Priority priority = PriorityArgumentType.getPriority(ctx, PRIORITY_ARG);
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
