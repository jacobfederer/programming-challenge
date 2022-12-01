package de.exxcellent.challenge;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example JUnit 5 test case.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    void itShouldOutputDay14WhenRunningWithSampleWeatherCsv() {

        App.main("src/main/resources/de/exxcellent/challenge/weather.csv");

        assertEquals("Day with smallest temperature spread : 14\n", outContent.toString());
    }


    @Test
    void itShouldLogErrorWhenRunningWithUnknownFile() {
        App.main("/unknown.csv");

        assertEquals("", outContent.toString());

        assertEquals("Unable to parse input file, err: java.io.FileNotFoundException: /unknown.csv (No such file or directory)", errContent.toString());
    }

    @Test
    void itShouldOutputAstonVillakWhenRunningWithSampleFootballCsvAndFootballFlag() {
        App.main("--football", "src/main/resources/de/exxcellent/challenge/football.csv");

        assertEquals("Team with smallest goal spread       : Aston_Villa\n", outContent.toString());
    }

    @Test
    void itShouldExitQuietlyWhenNoArgumentIsProvided() {
        App.main();

        assertEquals("", outContent.toString());
        assertEquals("", errContent.toString());
    }

}