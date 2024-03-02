package ru.andryss.sin;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static java.math.MathContext.DECIMAL128;

public class Approximator {
    /**
     * Calculate sin for given x using decomposition into power series
     * @param x point to calculate sin
     * @return sin(x)
     */
    public double approxSin(double x) {
        return approxSin(ranged(x), 10);
    }

    /**
     * Calculate sin for given x using decomposition into power series
     * @param x point to calculate sin between -pi and pi
     * @param count number of terms in decomposition (max 14)
     * @return sin(x)
     */
    public double approxSin(double x, int count) {
        if (x < -Math.PI || x > Math.PI) {
            throw new IllegalArgumentException("X must be in range [-pi;pi]");
        }
        if (count <= 0) {
            throw new IllegalArgumentException("Count must be more than zero");
        }
        if (count >= 15) {
            throw new IllegalArgumentException("Count must be less then 15 (possible precision loss)");
        }
        BigDecimal res = ZERO;
        for (int n = 1; n <= count; n++) {
            int k = (n << 1) - 1;
            BigDecimal part = valueOf(x).pow(k).divide(factorial(k), DECIMAL128);
            res = res.add(n % 2 == 0 ? part.negate() : part);
        }
        return res.doubleValue();
    }

    /**
     * Maps given x to x' in range [-pi;pi] so that sin(x') == sin(x)
     * @param x value to map
     * @return value in range [-pi;pi]
     */
    private double ranged(double x) {
        while (x > Math.PI) x -= 2 * Math.PI;
        while (x < -Math.PI) x += 2 * Math.PI;
        return x;
    }

    /**
     * Calculates factorial for given n
     * @param n factorial argument
     * @return factorial for given n
     */
    private BigDecimal factorial(int n) {
        BigDecimal res = ONE;
        for (int i = 2; i <= n; i++) {
            res = res.multiply(valueOf(i));
        }
        return res;
    }
}
