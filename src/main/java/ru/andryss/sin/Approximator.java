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
        return approxSin(ranged(x), 1e-6);
    }

    /**
     * Calculate sin for given x using decomposition into power series
     * @param x point to calculate sin between -pi and pi
     * @param precision calculation precision (e.g. 1e-6)
     * @return sin(x)
     */
    public double approxSin(double x, double precision) {
        x = ranged(x);
        if (precision <= 0) {
            throw new IllegalArgumentException("Precision must be more than zero");
        }
        BigDecimal res = ZERO;
        for (int n = 1; n <= 1000; n++) {
            int k = (n << 1) - 1;
            BigDecimal part = valueOf(x).pow(k).divide(factorial(k), DECIMAL128);
            res = res.add(n % 2 == 0 ? part.negate() : part);
            if (part.abs().doubleValue() < precision) {
                return res.doubleValue();
            }
        }
        throw new ArithmeticException("Precision can't be reached");
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
