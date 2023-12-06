package dev.runje.ana.year2023.day03;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.logging.Level;

import static dev.runje.ana.Helpers.getPath;
import static java.lang.Character.isDigit;

@Log
public class Day03 {

    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
        String inputFilePath = getPath("input", directory);
//        String inputFilePath = getPath("example", directory);
        int result1;
        int result2 = 0;
        ArrayList<Integer> gears = new ArrayList<>();
        HashMap<ArrayList<Integer>, ArrayList<Integer>> symbolsWithGears = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            String[] lines = br.lines().toArray(String[]::new);
            char[][] chars = new char[lines.length][lines[0].length()];
            for (int i = 0; i < chars.length; i++) {
                chars[i] = lines[i].toCharArray();
            }
            for (int i = 0; i < lines.length; i++) {
                String[] elements = Arrays
                        .stream(lines[i].split("\\D"))
                        .map(String::trim)
                        .filter(Predicate.isEqual("").negate())
                        .toArray(String[]::new);
                int elemEnd = 0;
                int elemStart = 0;
                for (String element: elements) {
                    boolean isGear = false;
                    int number = Integer.parseInt(element);
                    if (elemStart != 0) elemStart = lines[i].indexOf(element, elemEnd);
                    else elemStart = lines[i].indexOf(element);
                    elemEnd = elemStart + element.length() - 1;
                    int indexStart = elemStart == 0 ? elemStart : elemStart - 1;
                    int indexEnd = elemEnd == lines[i].length() - 1 ? elemEnd : elemEnd + 1;
                    int startRow = i == 0 ? i : i - 1;
                    int endRow = i == lines.length - 1 ? i : i + 1;
                    for (int j = startRow; j <= endRow; j++) {
                        for (int k = indexStart; k <= indexEnd; k++) {
                            if (!isDigit(chars[j][k]) && chars[j][k] != '.') {
                                isGear = true;
                                ArrayList<Integer> coordinates = new ArrayList<>();
                                coordinates.add(j);
                                coordinates.add(k);
                                ArrayList<Integer> numbers;
                                if (symbolsWithGears.containsKey(coordinates)) {
                                    numbers = symbolsWithGears.get(coordinates);
                                } else {
                                    numbers = new ArrayList<>();
                                }
                                numbers.add(number);
                                symbolsWithGears.put(coordinates, numbers);
                            }
                        }
                    }
                    if (isGear) {
                        gears.add(number);
                    }
                }
            }
            for (ArrayList<Integer> entry: symbolsWithGears.keySet()) {
                ArrayList<Integer> numbers = symbolsWithGears.get(entry);
                if (numbers.size()==2){
                    result2 += numbers.get(0) * numbers.get(1);
                }
            }

        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }

        result1 = gears.stream().mapToInt(a -> a).sum();
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }
}
