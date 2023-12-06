package dev.runje.ana.year2015.day01;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.logging.Level;

import static dev.runje.ana.Helpers.getPath;

@Log
public class Day01 {

    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
        String inputFilePath = getPath("input", directory);
//        String inputFilePath = getPath("example", directory);
        int result1 = 0;
        int result2 = 0;
        ArrayList<Integer> basement = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            for (String line: br.lines().toList()) {
                int i = 1;

                for (char c: line.toCharArray()) {
                    if (c == '('){
                        result1++;
                    } else if (c == ')') {
                        result1--;
                    }
                    if(result1 == -1){
                        basement.add(i);
                    }
                    i++;
                }
            }
            result2 = basement.get(0);
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }
}
