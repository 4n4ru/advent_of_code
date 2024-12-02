package dev.runje.ana.year2024.day02;

import lombok.extern.java.Log;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.logging.Level;

import static dev.runje.ana.Helpers.getPath;
import static java.lang.Math.abs;

@Log
public class Day02 {

    static int minStep = 1;
    static int maxStep = 3;
    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
        String inputFilePath = getPath("input", directory);
//        String inputFilePath = getPath("example", directory);
        int result1 = 0;
        int result2 = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            for (String line: br.lines().toList()) {
                int[] recordArray = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                if (isSafe(recordArray)) {
                    result1++;
                }
                else {
                    for (int i = 0; i < recordArray.length; i++) {
                        int[] temp = ArrayUtils.remove(recordArray, i);
                        if (isSafe(temp)) {
                            result2++;
                            break;
                        }
                    }
                }
            }
            result2 += result1;
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }

    private static boolean safeDistance(int[] recordArray) {
        boolean safe = false;
        for (int i = 0; i < recordArray.length - 1; i++) {
            int absDistance = abs(recordArray[i] - recordArray[i + 1]);
            if (absDistance >= minStep && absDistance <= maxStep){
                safe = true;
            }
            else {
                safe = false;
                break;
            }
        }
        return safe;
    }

    private static boolean isSafe(int[] recordArray) {
        int[] sortedAsc = recordArray.clone();
        Arrays.sort(sortedAsc);
        int[] sortedDesc = sortedAsc.clone();
        ArrayUtils.reverse(sortedDesc);
        if (Arrays.equals(recordArray, sortedAsc) || Arrays.equals(recordArray, sortedDesc)) {
            return safeDistance(recordArray);
        }
        return false;
    }

}
