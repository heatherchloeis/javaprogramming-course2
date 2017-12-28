
/**
 * Write a description of CountryExports here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class CountryExports {
    public void countryInfo(CSVParser parser, String countryOfInterest) {
        for (CSVRecord record: parser) {
            String country = record.get("Country");
            String exports = record.get("Exports");
            String value = record.get("Value (dollars)");
            if (country.contains(countryOfInterest)) {
                System.out.print(country + " : ");
                System.out.print(exports + " : ");
                System.out.println(value);
            }
        }
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record: parser) {
            String country = record.get("Country");
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(country);
            }
        }
    }
    public void numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record: parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                count++;
            }
        }
        System.out.println(count);
    }
    public void bigExporters(CSVParser parser, String minValue) {
        for (CSVRecord record: parser) {
            String country = record.get("Country");
            String value = record.get("Value (dollars)");
            if (value.length() > minValue.length()) {
                System.out.println(country + " " + value);
            }
        }
    }
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }    
}