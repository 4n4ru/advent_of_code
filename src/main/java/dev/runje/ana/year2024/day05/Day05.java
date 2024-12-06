package dev.runje.ana.year2024.day05;

import lombok.extern.java.Log;
import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.*;
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
        List<Pair<Integer, Integer>> rules = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            List <String> lines = br.lines().toList();
            for (String line: lines) {
                if (line.length() == 5) {
                    String[] stringPair = line.split("\\|");
                    Pair<Integer, Integer> rule = Pair.fromArray(Arrays.stream(stringPair).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new));
                    rules.add(rule);
                }
            }
            List<Integer> pagesList = createPagesList(rules);
            for (String line: lines){
                if(line.length()>5){
                    int[] update = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                    boolean isValid = true;
                    for (int i = 0; i < update.length - 1; i++) {
                        Pair<Integer, Integer> updatePair = new Pair<>(update[i], update[i+1]);
                        if (!rules.contains(updatePair)) {
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid) result1+= update[update.length/2];
                    else {
                        List<Integer> pagesToUpdate = pagesList.stream().filter(p -> Arrays.stream(update).boxed().toList().contains(p)).toList();
                        List<Integer> sortedPages = sortPages(rules, pagesToUpdate);
                        result2 += sortedPages.get(sortedPages.size()/2);
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }

    private static List<Integer> createPagesList(List<Pair<Integer, Integer>> rules) {
        List<Integer> pages = new ArrayList<>();
        for (Pair<Integer, Integer> rule: rules) {
            if (!pages.contains(rule.getValue0())) pages.add(rule.getValue0());
            if (!pages.contains(rule.getValue1())) pages.add(rule.getValue1());
        }
        return pages;
    }

    private static List<Integer> sortPages(List<Pair<Integer, Integer>> rules, List<Integer> pagesList) {
        Integer[] pagesArray = pagesList.toArray(Integer[]::new);
        for (int i = 0; i <= rules.size(); i++) {
            for (Pair<Integer, Integer> rule: rules) {
                int value0 = rule.getValue0();
                int value1 = rule.getValue1();
                if (Arrays.asList(pagesArray).contains(value0) && Arrays.asList(pagesArray).contains(value1)){
                    int index0 = Arrays.asList(pagesArray).indexOf(value0);
                    int index1 = Arrays.asList(pagesArray).indexOf(value1);
                    if (index0 > index1) {
                        pagesArray[index0] = value1;
                        pagesArray[index1] = value0;
                        break;
                    }
                }
            }
        }
        return Arrays.asList(pagesArray);
    }
}
