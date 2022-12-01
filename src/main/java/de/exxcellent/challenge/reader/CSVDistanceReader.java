package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.calculator.Distance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVDistanceReader implements DistanceReader {

    private final String labelColumnName;
    private final String minValueColumnName;
    private final String maxValueColumnName;

    public CSVDistanceReader(String labelColumnName, String minValueColumnName, String maxValueColumnName) {
        this.labelColumnName = labelColumnName;
        this.minValueColumnName = minValueColumnName;
        this.maxValueColumnName = maxValueColumnName;
    }

    public List<Distance> readDistancesFromUri(URI uri) throws IOException, DataInconsistentException {

        ArrayList<String[]> rows = readCSVRowsFromUri(uri);

        return findDistancesInRows(rows);
    }

    private ArrayList<String[]> readCSVRowsFromUri(URI uri) throws IOException {
        var rows = new ArrayList<String[]>();

        var file = new File(uri);

        var line = "";
        var splitBy = ",";

        var bufferedReader = new BufferedReader(new FileReader(file));

        while ((line = bufferedReader.readLine()) != null) {
            var columns = line.split(splitBy);

            rows.add(columns);
        }
        return rows;
    }

    private List<Distance> findDistancesInRows(ArrayList<String[]> rows) throws DataInconsistentException {
        var headerColumn = rows.get(0);

        var labelIndex = findColumnIndexByHeaderLabel(headerColumn, this.labelColumnName);
        var minValueIndex = findColumnIndexByHeaderLabel(headerColumn, this.minValueColumnName);
        var maxValueIndex = findColumnIndexByHeaderLabel(headerColumn, this.maxValueColumnName);

        return rows.stream()
                .skip(1)
                .filter(row -> {
                    var availableBodyIndices = row.length;
                    return labelIndex < availableBodyIndices && minValueIndex < availableBodyIndices && maxValueIndex < availableBodyIndices;
                })
                .map(row -> new Distance(row[labelIndex],
                        Integer.parseInt(row[minValueIndex]),
                        Integer.parseInt(row[maxValueIndex])))
                .collect(Collectors.toList());
    }

    private int findColumnIndexByHeaderLabel(String[] headerColumns, String headerLabel) throws DataInconsistentException {

        for (int i = 0; i < headerColumns.length; i++) {
            if (headerColumns[i].equalsIgnoreCase(headerLabel)) {
                return i;
            }
        }

        throw new DataInconsistentException(String.format("could not find header column %s, headers were %s", headerLabel, String.join(",", headerColumns)));
    }

}
