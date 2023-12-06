package dev.runje.ana.year2023.day02;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.logging.Level;

import static dev.runje.ana.Helpers.getPath;

@Log
public class Day02 {

    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
        String inputFilePath = getPath("input", directory);
//        String inputFilePath = getPath("example", directory);
        int result1 = 0;
        int result2 = 0;
        int redMax = 12;
        int greenMax = 13;
        int blueMax = 14;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            for (String line: br.lines().toList()) {
                String[] colourNumber = line.split("[;,:]");
                int redMin = 0;
                int greenMin = 0;
                int blueMin = 0;
                int gameNumber = Integer.parseInt(colourNumber[0].split(" ")[1]);
                for (int i = 1; i < colourNumber.length; i++) {
                    String[] pair = colourNumber[i].strip().split(" ");
                    int num = Integer.parseInt(pair[0]);
                    switch (pair[1]) {
                        case "blue" -> {
                            if (num > blueMax) gameNumber = 0;
                            if (num > blueMin) blueMin = num;
                        }
                        case "red" -> {
                            if (num > redMax) gameNumber = 0;
                            if (num > redMin) redMin = num;
                        }
                        case "green" -> {
                            if (Integer.parseInt(pair[0]) > greenMax) gameNumber = 0;
                            if (num > greenMin) greenMin = num;
                        }
                    }
                }
                result1 += gameNumber;
                result2 += greenMin * redMin * blueMin;
            }
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }
}
