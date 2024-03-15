package ru.andryss.galaxyguide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    @DisplayName("При передаче некорректного имени выбросилось исключение")
    public void createDescription_invalidOriginalName_throwException() {
        assertThrows(NullPointerException.class, () -> new Description(null, "a", List.of()));
        assertThrows(IllegalArgumentException.class, () -> new Description("", "a", List.of()));
    }

    @Test
    @DisplayName("При передаче некорректного описания выбросилось исключение")
    public void createDescription_invalidDescription_throwException() {
        assertThrows(NullPointerException.class, () -> new Description("a", null, List.of()));
        assertThrows(IllegalArgumentException.class, () -> new Description("a", "", List.of()));
    }

    @Test
    @DisplayName("При передаче некорректных характеристик выбросилось исключение")
    public void createDescription_invalidCharacteristics_throwException() {
        assertThrows(NullPointerException.class, () -> new Description("a", "a", null));
    }

    @Test
    @DisplayName("Описание создалось успешно")
    public void createDescription_validArguments_success() {
        String originalName = "orig";
        String descr = "desc";
        List<String> characteristics = List.of("c1", "c2");

        Description description = new Description(originalName, descr, characteristics);

        assertNotNull(description.originalName());
        assertEquals(originalName, description.originalName());
        assertNotNull(description.description());
        assertEquals(descr, description.description());
        assertNotNull(description.characteristics());
        assertEquals(characteristics, description.characteristics());
    }
}