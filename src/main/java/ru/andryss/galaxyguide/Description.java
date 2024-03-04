package ru.andryss.galaxyguide;

import java.util.List;

import static java.util.Objects.requireNonNull;

public record Description(String originalName, String description, List<String> characteristics) {
    public Description {
        if (requireNonNull(originalName).isEmpty()) {
            throw new IllegalArgumentException("заполни имя и возвращайся");
        }
        if (requireNonNull(description).isEmpty()) {
            throw new IllegalArgumentException("заполни описание и возвращайся");
        }
        requireNonNull(characteristics);
    }
}
