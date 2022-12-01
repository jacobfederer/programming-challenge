package de.exxcellent.challenge.calculator;

import java.util.List;

public class SmallestDistanceCalculator {

    public static Distance calculateSmallestDistance(List<Distance> distances) {

        Distance smallestDistance = null;

        int smallestSpread = -1;

        for (var d : distances) {

            var spread = Math.abs(d.maxValue - d.minValue);

            if (smallestDistance == null || spread <= smallestSpread) {
                smallestDistance = d;
                smallestSpread = spread;
            }
        }

        return smallestDistance;
    }
}
