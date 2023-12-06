package dev.runje.ana;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;

import java.lang.invoke.MethodHandles;
import java.util.logging.Level;

import static dev.runje.ana.Helpers.getPath;

@Log
public class Template {

    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
        String inputFilePath = getPath("input", directory);
//        String inputFilePath = getPath("example", directory);
        int result1 = 0;
        int result2 = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            for (String line: br.lines().toList()) {
                // code here
                System.out.println(line);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }

}
