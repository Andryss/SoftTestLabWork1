package ru.andryss.galaxyguide;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static java.lang.String.join;
import static java.util.Objects.requireNonNull;

@Slf4j
public class Whale {

    private final List<Organ> organs;

    public Whale(List<Organ> organs) {
        if (requireNonNull(organs).isEmpty()) {
            throw new IllegalArgumentException("заполни и возвращайся");
        }
        this.organs = organs;
    }

    public String createName(Description description) {
        requireNonNull(description);
        int feelingsCount = organs.stream().mapToInt(o -> o.getFeelings().size()).sum();
        if (feelingsCount == 0) {
            throw new IllegalStateException("Ни один орган ничего не почувствовал");
        }
        organs.forEach(organ -> {
            if (organ.getFeelings().isEmpty()) {
                return;
            }
            List<String> feelings = organ.getFeelings().stream().map(Feeling::name).toList();
            log.info("{} чувствует {}", organ.getName(), join(", ", feelings));
            organ.clear();
        });
        log.info("Я столкнулся с тем, что называют: {} ({})", description.originalName(), description.description());
        if (!description.characteristics().isEmpty()) {
            log.info("Этот объект {}", join(", ", description.characteristics()));
        }
        String name = generateName();
        log.info("Я назову это {}", name);
        return name;
    }

    private static final char[] vowels = {'а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю','я'};
    private static final char[] consonants = {'б', 'в', 'г', 'д', 'ж', 'з', 'к', 'л', 'м', 'н', 'п', 'р', 'с', 'т', 'ф', 'х', 'ц', 'ч'};

    private String generateName() {
        int count = 3 + (int) (Math.random() * 6);
        char[] name = new char[count];
        boolean isConsonant = true;
        for (int i = 0; i < count; i++) {
            if (isConsonant) {
                name[i] = consonants[(int) (Math.random() * consonants.length)];
            } else {
                name[i] = vowels[(int) (Math.random() * vowels.length)];
            }
            isConsonant = !isConsonant;
        }
        return String.valueOf(name);
    }

}