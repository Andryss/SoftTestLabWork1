package ru.andryss.sin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApproximatorTest {

    private static final double PRECISION = 1e-6;

    private final Approximator approximator = new Approximator();

    @DisplayName("Вычисление sin прошло успешно")
    @ParameterizedTest
    @CsvSource({
            "-3.1415926,  0.000000",
            "-2.6179938, -0.500000",
            "-2.0943951, -0.866025",
            "-1.5707963, -1.000000",
            "-1.0471975, -0.866025",
            "-0.5235987, -0.500000",
            " 0.0000000,  0.000000",
            " 0.5235987,  0.500000",
            " 1.0471975,  0.866025",
            " 1.5707963,  1.000000",
            " 2.0943951,  0.866025",
            " 2.6179938,  0.500000",
            " 3.1415926,  0.000000",
            " 5.0000000, -0.958924",
            "10.0000000, -0.544021",
            "20.0000000,  0.912945",
            "50.0000000, -0.262375",
            "99.9999999, -0.506365"
    })
    public void giveX_calcSin_success(double x, double expect) {
        double real = approximator.approxSin(x);
        double realPrecision = approximator.approxSin(x, PRECISION);

        assertEquals(expect, real, PRECISION);
        assertEquals(expect, realPrecision, PRECISION);
    }


    @DisplayName("Выбрасывается исключение при некорректных параметрах")
    @ParameterizedTest
    @CsvSource({
            " 0.0000000,  -1",
            " 0.0000000,   0",
            " 0.0000000,  -0.00000001"
    })
    public void giveIllegalParams_throwException(double x, double precision) {
        assertThrows(IllegalArgumentException.class, () -> approximator.approxSin(x, precision));
    }

}