package dev.runje.ana.year2023.day04;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.logging.Level;


import static dev.runje.ana.Helpers.getPath;
@Log
public class Day04 {
    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
        String inputFilePath = getPath("input", directory);
//        String inputFilePath = getPath("example", directory);
        int result1 = 0;
        int result2 = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            HashMap<Integer, Integer> instances = new HashMap<>();
            String[] lines = br.lines().toArray(String[]::new);
            for (String line : lines) {
                String[] card = line.split("[|,:]");
                String[] key = card[0].split(" ");
                instances.put(Integer.parseInt(key[key.length - 1]), 1);
            }
            for (int i = 0; i < lines.length; i++) {
                int winningNumbers = 0;
                String[] card = lines[i].split("[|,:]");
                String[] numbers = card[1].trim().split(" ");
                List<String> numbersList = new ArrayList<>(Arrays.asList(numbers));
                String[] hasNumbers = card[2].trim().split(" ");
                List<String> hasNumbersList = new ArrayList<>(Arrays.asList(hasNumbers));
                numbersList.removeAll(List.of(""));
                hasNumbersList.removeAll(List.of(""));
                int[] intNumbers = numbersList.stream().mapToInt(Integer::parseInt).toArray();
                int[] intHasNumbers = hasNumbersList.stream().mapToInt(Integer::parseInt).toArray();
                for (int number: intNumbers) {
                    if (Arrays.stream(intHasNumbers).anyMatch(k -> k == number)){
                        winningNumbers = winningNumbers + 1;
                    }
                }
                for (int j = 1; j <= winningNumbers; j++) {
                    int key = i+j+1;
                    if (instances.containsKey(key)){
                        int value1 = instances.get(key);
                        int value2 = instances.get(i+1);
                        instances.put(key, value1+value2);
                    }
                }
                int points = (int) Math.pow(2, winningNumbers - 1);
                result1 += points;
                result2 = instances.values().stream().mapToInt(Integer::valueOf).sum();
            }
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }
}
