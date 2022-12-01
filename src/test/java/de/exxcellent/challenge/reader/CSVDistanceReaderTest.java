package de.exxcellent.challenge.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CSVDistanceReaderTest {

    CSVDistanceReader reader = new CSVDistanceReader("Day", "MnT", "MxT");

    @Test()
    void itShouldReadMatchingRowCountAndSampleRowWhenReadingSampleCSVFile() throws IOException, URISyntaxException, DataInconsistentException {

        var weatherCSVURI = getClass().getClassLoader().getResource("de/exxcellent/challenge/weather.csv");

        var rows = reader.readDistancesFromUri(weatherCSVURI.toURI());

        assertEquals(30, rows.size(), "csv row count mismatch");

        var firstDistance = rows.get(0);

        assertEquals("1", firstDistance.label, "csv sample column label mismatch");

        assertEquals(59, firstDistance.minValue, "csv sample min value mismatch");

        assertEquals(88, firstDistance.maxValue, "csv sample max value mismatch");
    }


    @Test()
    void itShouldSilentlyDiscardRowsWithMissingBodyRows() throws IOException, URISyntaxException, DataInconsistentException {

        var weatherCSVURI = getClass().getClassLoader().getResource("de/exxcellent/challenge/reader/missing-body-column.csv");

        var rows = reader.readDistancesFromUri(weatherCSVURI.toURI());

        assertEquals(28, rows.size(), "csv row count mismatch");
    }

    @Test
    void itShouldThrowFileNotFoundExceptionWhenUnknownFileIsInput() {

        Assertions.assertThrows(FileNotFoundException.class, () -> {
            var path = Paths.get("./random.csv").toUri();

            reader.readDistancesFromUri(path);
        });

    }

    @Test
    void itShouldThrowDataInconsistentExceptionWhenFileDoesNotContainNeededLabels() {

        var dataInconsistentException = Assertions.assertThrows(DataInconsistentException.class, () -> {

            var missingHeaderCSVUri = getClass().getClassLoader().getResource("de/exxcellent/challenge/reader/missing-header.csv");

            reader.readDistancesFromUri(missingHeaderCSVUri.toURI());
        });

        assertEquals("could not find header column Day, headers were KJ,ABC,BCD", dataInconsistentException.getMessage());
    }


}
