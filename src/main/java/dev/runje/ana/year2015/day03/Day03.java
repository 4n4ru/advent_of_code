package dev.runje.ana.year2015.day03;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.logging.Level;

import static dev.runje.ana.Helpers.getPath;

@Log
public class Day03 {

    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
        String inputFilePath = getPath("input", directory);
//        String inputFilePath = getPath("example", directory);
        int result1 = 0;
        int result2 = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            Set<List<Integer>> uniqueCoordinates = new HashSet<>();
            List<String> input = br.lines().toList();
            for (String line: input) {
                int x = 0;
                int y = 0;
                uniqueCoordinates.add(Arrays.asList(x,y));
                for (char c: line.toCharArray()){
                    List<Integer> coordinate = move(c, x, y);
                    x = coordinate.get(0);
                    y = coordinate.get(1);
                    uniqueCoordinates.add(coordinate);
                }
            }
            result1 = uniqueCoordinates.size();
            Set<List<Integer>> uniqueCoordinates2 = new HashSet<>();
            for (String line: input){
                int x1 = 0;
                int y1 = 0;
                int x2 = 0;
                int y2 = 0;
                uniqueCoordinates2.add(Arrays.asList(x1,y1));
                char[] symbols = line.toCharArray();
                for (int i = 0; i < symbols.length; i++) {
                    char c = symbols[i];
                    List<Integer> coordinate;
                    if (i % 2 == 0){
                        coordinate = move(c, x1, y1);
                        x1 = coordinate.get(0);
                        y1 = coordinate.get(1);
                    } else {
                        coordinate = move(c, x2, y2);
                        x2 = coordinate.get(0);
                        y2 = coordinate.get(1);
                    }
                    uniqueCoordinates2.add(coordinate);
                }
                result2 = uniqueCoordinates2.size();
            }

        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }

    private static List<Integer> move(char c, int x, int y) {
        if (c == '^') y+=1;
        else if (c == 'v') y -= 1;
        else if (c == '<') x -= 1;
        else if (c == '>') x += 1;
        return Arrays.asList(x,y);
    }
}
