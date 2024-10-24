package me.tntz.ntfyNotifier.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.tntz.ntfyNotifier.ntfy.Priority;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;


public class PriorityArgumentType implements ArgumentType<Priority> {
    private static final String[] PRIORITY_POST_NAMES = Arrays.stream(Priority.values())
            .map((priority) -> priority.POST_NAME)
            .toArray(String[]::new);

    public static PriorityArgumentType priorityType() {
        return new PriorityArgumentType();
    }

    public static <S> Priority getPriority(CommandContext<S> context, String name) {
        return context.getArgument(name, Priority.class);
    }

    @Override
    public Priority parse(StringReader reader) throws CommandSyntaxException {
        String priorityPostName = reader.readUnquotedString();
        try {
            return Priority.fromPostName(priorityPostName);
        } catch (NoSuchElementException e) {
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.readerExpectedSymbol().createWithContext(
                    reader,
                    "Invalid priority. Use one of: " + String.join(", ", PRIORITY_POST_NAMES)
            );
        }
    }

    @Override
    public Collection<String> getExamples() {
        return Arrays.asList(PRIORITY_POST_NAMES);
    }
}
