
/**
 * Write a description of climateComparisons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class climateComparisons {
    public CSVRecord findMinValue(CSVRecord currentValue, CSVRecord minValue) {
        // if minValue is nothing
        if (minValue == null) {
            minValue = currentValue;
            // else
        } else {
            double currentTemp = Double.parseDouble(currentValue.get("Humidity"));
            double minTemp = Double.parseDouble(minValue.get("Humidity"));
            // check if currentValue temp is lower than minValue
            if (currentTemp < minTemp) {
                // if so, update minValue to currentValue
                minValue = currentValue;
            }
        }
        // minValue is the answer
        return minValue;
    }
    public CSVRecord leastHumidHourOfDay(CSVParser parser) {
        // start with minValue as nothing
        CSVRecord minValue = null;
        // for each row (currentValue) in the CSV file
        for (CSVRecord currentValue: parser) {
            minValue = findMinValue(currentValue, minValue);
        }
        return minValue;
    }
    public CSVRecord leastHumidHourToDate() {
        CSVRecord minValue = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files 
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get minValue in file
            CSVRecord currentValue = leastHumidHourOfDay(fr.getCSVParser());
            minValue = findMinValue(currentValue, minValue);
        }
        return minValue;
    }
    public void testDay() {
        FileResource fr = new FileResource();
        CSVRecord record = leastHumidHourOfDay(fr.getCSVParser());
        System.out.println("The least humid reading of the day was " + record.get("Humidity")
                            + " at " + record.get("DateUTC"));
    }
    public void testMultipleDays() {
        CSVRecord record = leastHumidHourToDate();
        System.out.println("The least humid reading of the day was " + record.get("Humidity")
                            + " at " + record.get("DateUTC"));
    }
}
