package ru.andryss.galaxyguide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FeelingTest {

    @Test
    @DisplayName("При передаче некорректного имени выбросилось исключение")
    public void createFeeling_invalidName_throwException() {
        assertThrows(NullPointerException.class, () -> new Feeling(null));
        assertThrows(IllegalArgumentException.class, () -> new Feeling(""));
    }
}