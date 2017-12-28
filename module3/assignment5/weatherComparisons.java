
/**
 * Write a description of weatherComparisons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class weatherComparisons {
    public CSVRecord coldestHourOfDay(CSVParser parser) {
        // start with minValue as nothing
        CSVRecord minRow = null;
        // for each row (currentRow) in the CSV file
        for (CSVRecord currentRow: parser) {
            // if minValue is nothing
            if (minRow == null) {
                minRow = currentRow;
            }
            // else
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double minTemp = Double.parseDouble(minRow.get("TemperatureF"));
                // check if currentRow's temp is lower than minValue
                if ((currentTemp < minTemp) && (currentTemp != -9999)) {
                    // if so, update minValue to currentRow
                    minRow = currentRow;
                }
            }
        }
        // the minRow is the answer
        return minRow;
    }
    public void testColdestHourOfDay() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourOfDay(fr.getCSVParser());
        System.out.println("The coldest temperature reading was " + coldest.get("TemperatureF"));
    }
    public CSVRecord coldestTempToDate() {
        CSVRecord minRow = null;
        DirectoryResource dr = new DirectoryResource();
        // iteratre over multiple files in directory
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourOfDay(fr.getCSVParser());
            // use method to get minRow
            if (minRow == null) {
                minRow = currentRow;                
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double minTemp = Double.parseDouble(minRow.get("TemperatureF"));
                // check if currentRow's temp is lower than minValue
                if (currentTemp < minTemp) {
                    // if so, update minValue to currentRow
                    minRow = currentRow;
                }
            }            
        }
        // the minRow is the answer
        return minRow;
    }
        public void testColdestTemp() {
        CSVRecord coldest = coldestTempToDate();
        System.out.println("The coldest temperature reading was " + coldest.get("TemperatureF")
                            + " on " + coldest.get("DateUTC") + " for the days selected.");
    }
    public void testColdestTempToDate() {
        CSVRecord coldest = coldestTempToDate();
        System.out.println("The coldest temperature reading was " + coldest.get("TemperatureF")
                            + " on " + coldest.get("DateUTC") + " for the days selected.");
    }
}
