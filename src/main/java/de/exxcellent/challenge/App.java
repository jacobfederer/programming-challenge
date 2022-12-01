package de.exxcellent.challenge;

import de.exxcellent.challenge.calculator.SmallestDistanceCalculator;
import de.exxcellent.challenge.reader.CSVDistanceReader;
import de.exxcellent.challenge.reader.DataInconsistentException;
import de.exxcellent.challenge.reader.DistanceReader;
import de.exxcellent.challenge.writer.DistanceConsoleWriter;
import de.exxcellent.challenge.writer.DistanceWriter;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        if (args.length == 0) {
            return;
        }

        boolean footballMode = args[0].equalsIgnoreCase("--football");
        var filePathIndex = 0;

        DistanceReader reader;
        DistanceWriter writer;

        if (footballMode) {
            reader = new CSVDistanceReader("Team", "Goals", "Goals Allowed");
            writer = new DistanceConsoleWriter(System.out, System.err, "Team with smallest goal spread       : %s%n");
            filePathIndex += 1;
        } else {
            reader = new CSVDistanceReader("Day", "MnT", "MxT");
            writer = new DistanceConsoleWriter(System.out, System.err, "Day with smallest temperature spread : %s%n");
        }

        var path = Paths.get(args[filePathIndex]);

        try {
            var distances = reader.readDistancesFromUri(path.toUri());

            var distance = SmallestDistanceCalculator.lookupSmallestSpread(distances);

            if (distance != null) {
                writer.writeDistance(distance);
            }
        } catch (IOException | DataInconsistentException e) {
            writer.writeParseError(e);
        }
    }
}
