package dev.runje.ana.year2023.day06;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.logging.Level;

import static dev.runje.ana.Helpers.getPath;

@Log
public class Day06 {

    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
        String inputFilePath = getPath("input", directory);
//        String inputFilePath = getPath("example", directory);
        int result1 = 1;
        int result2 = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            String[] lines = br.lines().toList().toArray(new String[0]);
            int[] times = Arrays.stream(lines[0].replace("Time:", "").split(" ")).filter(x -> !x.isEmpty()).mapToInt(Integer::parseInt).toArray();
            int time2 = Integer.parseInt(lines[0].replace("Time:", "").replace(" ", ""));
            System.out.println(time2);
            int[] distances = Arrays.stream(lines[1].replace("Distance:", "").split(" ")).filter(x -> !x.isEmpty()).mapToInt(Integer::parseInt).toArray();
            long distance2 = Long.parseLong(lines[1].replace("Distance:", "").replace(" ", ""));
            System.out.println(distance2);

            for (int i = 0; i < times.length; i++) {
                int waysToWin = 0;
                for (int j = 0; j < times[i]; j++) {
                    int distance = (times[i] - j)*j;
                    if (distance > distances[i]) waysToWin++;
                }
                result1 *= waysToWin;
            }
            int waysToWin2 = 0;
            for (int i = 0; i < time2; i++) {
                long distance = (long) (time2 - i) *i;
                if (distance > distance2) waysToWin2++;
            }
            result2 = waysToWin2;

        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }
}
