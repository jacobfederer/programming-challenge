package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.calculator.Distance;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public interface DistanceReader {
    List<Distance> readDistancesFromUri(URI uri) throws IOException, DataInconsistentException;
}
