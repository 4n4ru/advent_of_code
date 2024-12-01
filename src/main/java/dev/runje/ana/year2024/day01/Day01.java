package dev.runje.ana.year2024.day01;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import static dev.runje.ana.Helpers.getPath;
import static java.lang.Math.abs;

@Log
public class Day01 {

    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
        String inputFilePath = getPath("input", directory);
//        String inputFilePath = getPath("example", directory);
        int result1 = 0;
        int result2 = 0;
        List<Integer> left = new java.util.ArrayList<>(List.of());
        List<Integer> right = new java.util.ArrayList<>(List.of());
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            for (String line: br.lines().toList()) {
                // code here
                String[] temp = line.split(" ");
                left.add(Integer.valueOf(temp[0]));
                right.add(Integer.valueOf(temp[temp.length-1]));
            }
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        Collections.sort(left);
        Collections.sort(right);
        for (int i = 0; i < left.size(); i++) {
            result1 = result1 + abs(left.get(i)-right.get(i));
            result2 = result2 + left.get(i) * Collections.frequency(right,left.get(i));
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }

}
