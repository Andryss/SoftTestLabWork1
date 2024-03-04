package ru.andryss.galaxyguide;

import static java.util.Objects.requireNonNull;

public record Feeling(String name) {
    public Feeling {
        if (requireNonNull(name).isEmpty()) {
            throw new IllegalArgumentException("заполни и возвращайся");
        }
    }
}