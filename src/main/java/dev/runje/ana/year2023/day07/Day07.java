package dev.runje.ana.year2023.day07;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static dev.runje.ana.Helpers.getPath;

@Log
public class Day07 {

    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
//        String inputFilePath = getPath("input", directory);
        String inputFilePath = getPath("example", directory);
        int result1 = 0;
        int result2 = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            String[] bet;
            for (String line: br.lines().toList()) {
                bet = line.split(" ");
                String hand = bet[0];
                System.out.println(hand);
                String sortedHand = hand.chars()
                        .mapToObj(i -> (char)i)
                        .collect(Collectors.groupingBy(
                            String::valueOf, LinkedHashMap::new, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .peek(System.out::println)
                        .map(e -> e.getKey().repeat(e.getValue().intValue()))
                        .collect(Collectors.joining(""));
                System.out.println(sortedHand);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }
}
