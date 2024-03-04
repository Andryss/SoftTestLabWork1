package ru.andryss.galaxyguide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WhaleTest {

    @Test
    @DisplayName("При передаче некорректных органов выбросилось исключение")
    public void createWhale_invalidOrgans_throwException() {
        assertThrows(NullPointerException.class, () -> new Whale(null));
        assertThrows(IllegalArgumentException.class, () -> new Whale(List.of()));
    }

    @Test
    @DisplayName("При передаче некорректного описания выбросилось исключение")
    public void createName_invalidDescription_throwException() {
        Whale whale = new Whale(List.of(new Organ("a")));
        assertThrows(NullPointerException.class, () -> whale.createName(null));
    }

    @Test
    @DisplayName("При создании имени без чувствования органов выбросилось исключение")
    public void createName_organsNotFelt_throwException() {
        Whale whale = new Whale(List.of(new Organ("a")));
        assertThrows(IllegalStateException.class, () -> whale.createName(new Description("a", "a", List.of())));
    }

    @Test
    @DisplayName("При создании имени вернулось корректное имя")
    public void createName_organsFelt_returnValidName() {
        Organ organ = new Organ("a");
        Whale whale = new Whale(List.of(organ));
        organ.feel(new Feeling("a"));

        String name = whale.createName(new Description("a", "a", List.of()));

        assertNotNull(name);
        assertTrue(name.length() > 2);
    }

    @Test
    @DisplayName("При создании имени чувства всех органов очистились")
    public void createName_organsFelt_clearOrgansFeelings() {
        Organ organ1 = new Organ("a"), organ2 = new Organ("a");
        Whale whale = new Whale(List.of(organ1, organ2));
        organ1.feel(new Feeling("a"));
        organ2.feel(new Feeling("a"));

        whale.createName(new Description("a", "a", List.of()));

        assertTrue(organ1.getFeelings().isEmpty());
        assertTrue(organ2.getFeelings().isEmpty());
    }
}