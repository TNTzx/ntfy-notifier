package me.tntz.ntfyNotifier.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import java.util.*;


public class TagsArgumentType implements ArgumentType<String[]> {
    public static <S> String[] getTags(CommandContext<S> context, String name) {
        return context.getArgument(name, String[].class);
    }

    @Override
    public String[] parse(StringReader reader) throws CommandSyntaxException {
        if (reader.peek() != '[') throw new IllegalArgumentException("Tags must start with opening brace \"[\".");
        reader.skip();

        List<String> output = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        while (reader.canRead()) {
            if (reader.peek() == ']') {
                reader.skip();
                reader.skipWhitespace();
                break;
            }

            String tag = reader.readUnquotedString();
            output.add(tag);
            if (reader.peek() != ',') {
                throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.readerExpectedSymbol().createWithContext(reader, "Expected comma");
            }
            reader.skip();
            reader.skipWhitespace();
        }

        return output.toArray(new String[]{});
    }

    @Override
    public Collection<String> getExamples() {
        return List.of(
                "[tag1]",
                "[tag1, tag2, tag3, tagLast]"
        );
    }
}
