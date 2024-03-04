package ru.andryss.galaxyguide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganTest {

    @Test
    @DisplayName("При передаче некорректного имени выбросилось исключение")
    public void createOrgan_invalidName_throwException() {
        assertThrows(NullPointerException.class, () -> new Organ(null));
        assertThrows(IllegalArgumentException.class, () -> new Organ(""));
    }

    @Test
    @DisplayName("При чувствовании некорректных чувств выбросилось исключение")
    public void organFeel_invalidFeelings_throwException() {
        Organ organ = new Organ("a");
        assertThrows(NullPointerException.class, () -> organ.feel((Feeling[]) null));
        assertThrows(NullPointerException.class, () -> organ.feel((Feeling) null));
        assertThrows(NullPointerException.class, () -> organ.feel(new Feeling("a"), null));
    }

    @Test
    @DisplayName("При чувствовании чувство добавилось в список")
    public void organFeel_validFeelings_feelingsAdded() {
        Organ organ = new Organ("a");
        Feeling aFeeling = new Feeling("a");
        organ.feel(aFeeling);

        Feeling feeling = organ.getFeelings().get(0);

        assertEquals(aFeeling, feeling);
    }

    @Test
    @DisplayName("После очистки чувств они не возвращаются")
    public void clearFeelings_feelingsNotEmpty_feelingsCleared() {
        Organ organ = new Organ("a");
        organ.feel(new Feeling("a"));

        organ.clear();

        assertEquals(0, organ.getFeelings().size());
    }
}