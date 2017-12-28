
/**
 * Write a description of weatherTempAndHumidity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class weatherTempAndHumidity {
    public double findAverage(CSVParser parser, String weather) {
        double sumTemp = 0;
        int count = 0;
        // for each row (currentTemp) in the CSV file
        for (CSVRecord currentRow: parser) {
            // update sumTemp to include currentFile
            double currentTemp = Double.parseDouble(currentRow.get(weather));
            sumTemp = sumTemp + currentTemp;
            // update count
            count++;
        }
        // calculate average by dividing sumTemp by count
        double averageTemp = sumTemp / count;
        return averageTemp;
    }
    public double findAverageWith2Conditions(CSVParser parser, String weather1, String weather2, Integer value) {
        double sumTemp = 0;
        int count = 0;
        for (CSVRecord currentRow: parser) {
            double currentTemp = Double.parseDouble(currentRow.get(weather1));
            double currentHumidity = Double.parseDouble(currentRow.get(weather2));
            if (currentHumidity >= value) {
                sumTemp = sumTemp + currentTemp;
                count++;
            }
        }
        double averageTemp = sumTemp / count;
        return averageTemp;
    }
        public void testAverageTemp() {
        FileResource fr = new FileResource();
        double averageTemp = findAverage(fr.getCSVParser(), "TemperatureF");
        System.out.println("The average temperature was " + averageTemp);
    }
    public void testAverageTempWith2Conditions() {
        FileResource fr = new FileResource();
        double averageTemp = findAverageWith2Conditions(fr.getCSVParser(), "TemperatureF", "Humidity", 80);
        System.out.println("The average temperature was " + averageTemp + " when humidity was higher than 80.");
    }
}
