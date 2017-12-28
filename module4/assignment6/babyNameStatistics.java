
/**
 * Write a description of babyNameStatistics here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.lang.Object;
import java.io.File;
import org.apache.commons.csv.*;

public class babyNameStatistics {
    public void retrieveBabyNames() {
        FileResource fr = new FileResource();
        for (CSVRecord record: fr.getCSVParser(false)) {
            System.out.print("Name: " + record.get(0) + " || ");
            System.out.print("Gender: " + record.get(1) + " || ");
            System.out.println("Population born: " + record.get(2));
        }
    }
    public void retrieveNameStatistics(FileResource fr) {
        int namesF = 0;
        int namesM = 0;
        int namesTotal = 0;
        for (CSVRecord record: fr.getCSVParser(false)) {
            if (record.get(1).equals("F")) {
                namesF++;
            }
            if (record.get(1).equals("M")) {
                namesM++;
            }
        }
        namesTotal = namesF + namesM;
        System.out.println("Total female names is " + namesF);
        System.out.println("Total male names is " + namesM);
        System.out.println("Total names is " + namesTotal);
    }
    public int retrieveNameRank(FileResource fr, String name, String gender) {
        // initialize count
        int count = 0;
        int rankPop = 0;
        for (CSVRecord record: fr.getCSVParser(false)) {
            // while record.get(1) equals "gender"
            if (record.get(1).equals(gender)) {
                // if record.get(0) equals "name"
                if (record.get(0).equals(name)) {
                    // population equals record.get(2)
                    rankPop = Integer.parseInt(record.get(2));
                }
                // if record.get(2) is greater than or equal to population
                if ((Integer.parseInt(record.get(2)) > rankPop) && (record.get(1).equals(gender))) {
                    // count++
                    count++;
                }
            }
        }
        // count equals rank
        return count;
    }
    public String retrieveNameSameRank(FileResource fr, String gender, Integer rank) {
        // initialize count
        int count = 0;
        String nameSameRank = "";
        for (CSVRecord record: fr.getCSVParser(false)) {
            // while record(1) equals "gender"
            if (record.get(1).equals(gender)) {
                //count++
                count++;
                // if count = rank
                if (count == rank) {
                    // retrieve baby name
                    nameSameRank = record.get(0);
                }
           }
        }
        // return baby name
        return nameSameRank;
    }
    public void retrieveMaxRank() {
        String name = "Susan";
        String gender = "F";
        int maxRank = 0;
        String year = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currentRank = retrieveNameRank(fr, name, gender);
            if (maxRank == 0) {
                maxRank = currentRank;
                year = f.getName();
                year = year.substring(3, 7);
            } else {
                if ((currentRank < maxRank) && (currentRank != -1)) {
                    maxRank = currentRank;
                    year = f.getName();
                    year = year.substring(3, 7);
                }
            }
        }
        System.out.println("The name " + name + " ranked highest in the year " + 
                          year + " at " + maxRank);
    }
    public double retrieveAverageRank(String gender, String name) {
        DirectoryResource dr = new DirectoryResource();
        int count = 0;
        int sumRank = 0;
        for (File f: dr.selectedFiles()) {
            count++;
            FileResource fr = new FileResource(f);
            int pivot = 0;
            int currentRank = 0;
            for (CSVRecord record: fr.getCSVParser(false)) {
                if(record.get(1).equals(gender)) {
                    pivot++;
                    if (record.get(0).equals(name)) {
                        currentRank = pivot;
                        break;
                    }
                }
            }
            sumRank += currentRank;
        }
        if (sumRank == 0) {
            return -1;
        }
        else {
            return (double)(sumRank)/count;
        }
    }
    public void testAverageRank() {
        String name = "Susan";
        String gender = "F";
        double rank = retrieveAverageRank(gender, name);
        System.out.println("The average rank for the name " + name + " is " + rank);
    }
    public int retrieveTotalPop(FileResource fr, String gender, String name) {
        int totalPop = 0;
        int popOfInterest = 0;
        for (CSVRecord record: fr.getCSVParser(false)) {
            // while record.get(1) equals "gender"
            if (record.get(1).equals(gender)) {
                // if record.get(0) equals "name"
                if (record.get(0).equals(name)) {
                    // population equals record.get(2)
                    popOfInterest = Integer.parseInt(record.get(2));
                }
                // if record.get(2) is greater than or equal to population
                if ( Integer.parseInt(record.get(2)) > popOfInterest) {
                    // count++
                    totalPop += Integer.parseInt(record.get(2));
                }
            }
        }
        return totalPop;
    }
    public void testPopRetrieval() {
        FileResource fr = new FileResource();
        String name = "Drew";
        String gender = "M";
        int totalPop = retrieveTotalPop(fr, gender, name);
        System.out.println("In the year 1990, " + totalPop + 
                           " total girls were born with names more popular than " + name);
    }
    public void testNameStats() {
        FileResource fr = new FileResource();
        String name = retrieveNameSameRank(fr, "M", 450);
        System.out.println("The name " + name + "ranked 450 for this year.");
    }
    public void testNameRank() {
        String name = "Mich";
        String gender = "M";
        int rank = 0;
        String year = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            rank = retrieveNameRank(fr, name, gender);
            year = f.getName();
            year = year.substring(3, 7);
            System.out.println("The name " + name + " ranked " + rank + " in the year " + year);
        }
    }
    /*public void testNameComparison() {
        int rank = 0;
        String name = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            rank = retrieveNameRank(fr, "Owen", "M");
        }
        for (File f2: dr.selectedFiles()) {
            FileResource fr2 = new FileResource(f2);
            name = retrieveNameSameRank(fr2, "M", rank);
        }
        System.out.println("In the year 1974, the name Owen ranked " + rank + 
                            " out of all boy baby names.");
        System.out.println("In the year 2014, the name " + name + " was just as popular as Owen in 1974.");
    }*/
}
