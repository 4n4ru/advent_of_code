package dev.runje.ana.year2015.day05;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.logging.Level;

import static dev.runje.ana.Helpers.getPath;

@Log
public class Day05 {
    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
        String inputFilePath = getPath("input", directory);
//        String inputFilePath = getPath("example", directory);
        int result1 = 0;
        int result2 = 0;
        String[] naughtyStrings = {"ab", "cd", "pq", "xy"};
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            for (String line: br.lines().toList()) {
                int numVowels = line.replaceAll("[^aeiou]", "").length();
                char[] chars = line.toCharArray();
                int numDoubleLetters = 0;
                for (int i = 1; i < chars.length; i++) {
                    if (chars[i - 1] == chars[i]) {
                        numDoubleLetters = 1;
                        break;
                    }
                }
                int numNaughtyStrings = 0;
                for (String s: naughtyStrings) {
                    if(line.contains(s)) {
                        numNaughtyStrings++;
                        break;
                    }
                }
                if (numVowels >= 3 && numDoubleLetters > 0 && numNaughtyStrings == 0) result1++;
                boolean doubleLetter = false;
                boolean repeats = false;
                for (int i = 1; i < chars.length; i++) {
                    String twoLetters = "" + chars[i-1] + chars[i];
                    String lineAfter = line.replace(twoLetters, "*" );
                    lineAfter = lineAfter.replaceAll("[^*]", "");
                    if(lineAfter.length() >= 2) {
                        doubleLetter = true;
                        break;
                    }
                }
                for (int i = 2; i < chars.length; i++) {
                    if (chars[i-2] == chars[i]){
                        repeats = true;
                        break;
                    }
                }
                if(doubleLetter && repeats) result2++;
            }
        } catch (Exception e) {
                log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }
}
