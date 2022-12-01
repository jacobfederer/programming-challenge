package de.exxcellent.challenge.calculator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmallestDistanceCalculatorTest {

    Distance abc = new Distance("ABC", 0, 99);
    Distance def = new Distance("BCD", 40, 50);
    Distance ghi = new Distance("GHI", 44, 46);

    @Test
    void itShouldReturnSmallestDistanceWhenComparingMultipleDistances() {

        var calculator = new SmallestDistanceCalculator();

        var distance = calculator.calculateSmallestDistance(Arrays.asList(abc, def, ghi));

        assertEquals("GHI", distance.label, "cannot find smallest distance");

    }

    @Test
    void itShouldReturnSmallestAbsoluteDistanceWhenComparingDistancesWithSpread() {

        Distance jkl = new Distance("JKL", 60, 40);

        var calculator = new SmallestDistanceCalculator();

        var distance = calculator.calculateSmallestDistance(Arrays.asList(jkl, def, ghi));

        assertEquals("GHI", distance.label, "cannot find smallest distance");

    }

}
