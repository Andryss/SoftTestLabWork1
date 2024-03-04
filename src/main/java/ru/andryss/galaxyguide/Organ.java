package ru.andryss.galaxyguide;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

@Getter
public class Organ {

    private final String name;
    private final List<Feeling> feelings;

    public Organ(String name) {
        if (requireNonNull(name).isEmpty()) {
            throw new IllegalArgumentException("заполни имя и возвращайся");
        }
        this.name = name;
        this.feelings = new ArrayList<>();
    }

    public void feel(Feeling... newFeelings) {
        for (Feeling feeling : requireNonNull(newFeelings)) {
            requireNonNull(feeling);
        }
        feelings.addAll(asList(newFeelings));
    }

    public void clear() {
        feelings.clear();
    }
}
