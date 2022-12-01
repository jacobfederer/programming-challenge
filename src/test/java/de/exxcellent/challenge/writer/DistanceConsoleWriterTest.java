package de.exxcellent.challenge.writer;

import de.exxcellent.challenge.calculator.Distance;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceConsoleWriterTest {


    @Test
    void itShouldWriteDistanceToConsole() {

        var outContent = new ByteArrayOutputStream();
        var printStream = new PrintStream(outContent);

        var errContent = new ByteArrayOutputStream();
        var errStream = new PrintStream(errContent);

        var consoleWriter = new DistanceConsoleWriter(printStream, errStream, "Day with smallest temperature spread : %s%n");

        var sampleDistance = new Distance("ABCDEF", 0, 2);

        consoleWriter.writeDistance(sampleDistance);

        assertEquals("Day with smallest temperature spread : ABCDEF\n", outContent.toString());


    }
}
