package dev.runje.ana.year2023.day01;

import lombok.extern.java.Log;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.util.logging.Level;

import static dev.runje.ana.Helpers.getPath;

@Log
public class Day01 {

    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
        String inputFilePath = getPath("input", directory);
//        String inputFilePath = getPath("example", directory);
        int result1 = 0;
        int result2 = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            for (String line: br.lines().toList()) {
                result1 += findNumber(line);

                String numberString = convertToNumberString(line);
                result2 += findNumber(numberString);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }

    private static String convertToNumberString(String line) {

        return line
                .replace("one", "o1ne")
                .replace("two", "t2wo")
                .replace("three", "t3hree")
                .replace("four", "f4our")
                .replace("five", "f5ive")
                .replace("six", "s6ix")
                .replace("seven", "s7even")
                .replace("eight", "e8ight")
                .replace("nine", "n9ine");
    }

    private static int findNumber(String line) {
        String newline = line.replaceAll("[^\\d]", "");
        if (newline.isEmpty()) return 0;
        char first = newline.charAt(0);
        char last = newline.charAt(newline.length()-1);
        return Character.getNumericValue(first)*10 + Character.getNumericValue(last);
    }

}
