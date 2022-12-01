package de.exxcellent.challenge.writer;

import de.exxcellent.challenge.calculator.Distance;

import java.io.PrintStream;

public class DistanceConsoleWriter implements DistanceWriter {

    PrintStream successPrinter;
    PrintStream errorPrinter;

    String resultTemplate;

    public DistanceConsoleWriter(PrintStream successStream, PrintStream errorStream, String resultTemplate) {
        this.successPrinter = successStream;
        this.errorPrinter = errorStream;
        this.resultTemplate = resultTemplate;
    }


    public void writeDistance(Distance distance) {
        successPrinter.printf(resultTemplate, distance.label);
    }

    public void writeParseError(Exception exception) {
        errorPrinter.printf("Unable to parse input file, err: %s", exception);
    }
}
