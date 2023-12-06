package dev.runje.ana.year2015.day02;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
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
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            for (String line: br.lines().toList()) {
                String[] dimensions = line.split("x");
                int[] intDimension = Arrays.stream(dimensions).mapToInt(Integer::parseInt).toArray();
                int[] sortedDimension = Arrays.stream(intDimension).sorted().toArray();
                int[] surfaces = {intDimension[0]*intDimension[1], intDimension[0] * intDimension[2], intDimension[1] * intDimension[2]};
                int[] sortedSurfaces = Arrays.stream(surfaces).sorted().toArray();
                int sum = Arrays.stream(sortedSurfaces).sum();
                int cube = Arrays.stream(sortedDimension).reduce((val1, val2) -> val1*val2).orElse(0);
                result1 += 2 * sum + sortedSurfaces[0];
                result2 += (sortedDimension[0] + sortedDimension[1]) * 2 + cube;

            }
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }

}
