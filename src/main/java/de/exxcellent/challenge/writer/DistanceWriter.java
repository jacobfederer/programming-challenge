package de.exxcellent.challenge.writer;

import de.exxcellent.challenge.calculator.Distance;

public interface DistanceWriter {
    void writeDistance(Distance distance);

    void writeParseError(Exception error);
}
