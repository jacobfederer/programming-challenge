package de.exxcellent.challenge.reader;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public interface DistanceReader {
    List<Distance> readDistancesFromUri(URI uri) throws IOException, DataInconsistentException;
}
