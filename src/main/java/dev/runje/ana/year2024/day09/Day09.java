package dev.runje.ana.year2024.day09;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

import static dev.runje.ana.Helpers.getPath;

@Log
public class Day09 {
    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
        String inputFilePath = getPath("input", directory);
//        String inputFilePath = getPath("example", directory);
        BigInteger result1 = BigInteger.valueOf(0);
        int result2 = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            String line = br.readLine();
            List<String> files = new ArrayList<>();
            int j = 0;
            for (int i = 0; i < line.length(); i++) {
                int reps = Integer.parseInt(line.substring(i,i+1));
                if (i%2==0){
                    for (int k = 0; k < reps; k++) {
                        files.add(String.valueOf(j));
                    }
                    j++;
                } else {
                    for (int k = 0; k < reps; k++) {
                        files.add(".");
                    }
                }
            }
            int numDots = Collections.frequency(files, ".");
            int[] rearrangedFiles = new int[files.size() - numDots + 1];
            int k = files.size() - 1;
            for (int i = 0; i != k + 1; i++) {
                if (Objects.equals(files.get(i), ".")){
                    for (; k >= 0 ; k--) {
                        if (!Objects.equals(files.get(k), ".")) {
                            rearrangedFiles[i] = Integer.parseInt(files.get(k));
                            k--;
                            break;
                        }
                    }
                } else rearrangedFiles[i] = Integer.parseInt(files.get(i));
            }
            for (int i = 0; i < rearrangedFiles.length; i++) {
                BigInteger elem = BigInteger.valueOf(i).multiply(BigInteger.valueOf(rearrangedFiles[i]));
                result1 = result1.add(elem);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }
}
