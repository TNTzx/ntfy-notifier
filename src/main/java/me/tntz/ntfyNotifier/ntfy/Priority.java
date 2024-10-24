package me.tntz.ntfyNotifier.ntfy;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public enum Priority {
    MAX("max", 5),
    HIGH("high", 4),
    DEFAULT("default", 3),
    LOW("low", 2),
    MIN("min", 1);

    public final String POST_NAME;
    public final int ID;
    Priority(String postName, int id) {
        this.POST_NAME = postName;
        this.ID = id;
    }

    public static Priority fromPostName(String postName) throws NoSuchElementException {
        return Arrays.stream(Priority.values())
                .filter(priority -> Objects.equals(priority.POST_NAME, postName))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("No such priority with post name \"" + postName + "\"."));
    }
}
