package dev.runje.ana.year2024.day04;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.List;
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
            List<String> input = br.lines().toList();
            for (int i = 0; i < input.size(); i++) {
                for (int j = input.get(i).indexOf('X'); j >= 0; j = input.get(i).indexOf('X', j + 1)) {
                    result1 += numXmasFound(input, i, j);
                }
                for (int j = input.get(i).indexOf('A'); j >= 0; j = input.get(i).indexOf('A', j + 1)) {
                    result2 += numXedMasFound(input, i, j);
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }

    private static int numXedMasFound(List<String> input, int i, int j) {
        int numFound = 0;
        if (checkRightDiagonalMas(input, i, j) && checkLeftDiagonalMas(input, i, j)) numFound++;
        if (checkRightDiagonalMas(input, i, j) && checkLeftDiagonalReverseMas(input, i, j)) numFound++;
        if (checkRightDiagonalReverseMas(input, i, j) && checkLeftDiagonalMas(input, i, j)) numFound++;
        if (checkRightDiagonalReverseMas(input, i, j) && checkLeftDiagonalReverseMas(input, i, j)) numFound++;
        return numFound;
    }

    private static boolean checkLeftDiagonalReverseMas(List<String> input, int i, int j) {
        if (i + 1 < input.size() && i - 1 >= 0 && j - 1 >= 0 && j + 1 < input.get(i).length()) {
            char c0 = input.get(i + 1).charAt(j - 1);
            char c1 = input.get(i).charAt(j);
            char c2 = input.get(i - 1).charAt(j + 1);
            String word = "" + c0 + c1 + c2;
            return word.equals("MAS");
        }
        return false;
    }

    private static boolean checkRightDiagonalReverseMas(List<String> input, int i, int j) {
        if (i + 1 < input.size() && i - 1 >= 0 && j - 1 >= 0 && j + 1 < input.get(i).length()) {
            char c0 = input.get(i + 1).charAt(j + 1);
            char c1 = input.get(i).charAt(j);
            char c2 = input.get(i - 1).charAt(j - 1);
            String word = "" + c0 + c1 + c2;
            return word.equals("MAS");
        }
        return false;
    }

    private static boolean checkLeftDiagonalMas(List<String> input, int i, int j) {
        if (i + 1 < input.size() && i - 1 >= 0 && j - 1 >= 0 && j + 1 < input.get(i).length()) {
            char c0 = input.get(i - 1).charAt(j + 1);
            char c1 = input.get(i).charAt(j);
            char c2 = input.get(i + 1).charAt(j - 1);
            String word = "" + c0 + c1 + c2;
            return word.equals("MAS");
        }
        return false;
    }

    private static boolean checkRightDiagonalMas(List<String> input, int i, int j) {
        if (i + 1 < input.size() && i - 1 >= 0 && j - 1 >= 0 && j + 1 < input.get(i).length()) {
            char c0 = input.get(i - 1).charAt(j - 1);
            char c1 = input.get(i).charAt(j);
            char c2 = input.get(i + 1).charAt(j + 1);
            String word = "" + c0 + c1 + c2;
            return word.equals("MAS");
        }
        return false;
    }


    private static int numXmasFound(List<String> input, int i, int j) {
        int numFound = 0;
        if (checkHorizontal(input, i , j)) numFound++;
        if (checkHorizontalReverse(input, i, j)) numFound++;
        if (checkVertical(input, i , j)) numFound++;
        if (checkVerticalReverse(input, i, j)) numFound++;
        if (checkRightDiagonal(input, i , j)) numFound++;
        if (checkLeftDiagonal(input, i , j)) numFound++;
        if (checkLeftDiagonalReverse(input, i, j)) numFound++;
        if (checkRightDiagonalReverse(input, i, j)) numFound++;
        return numFound;
    }

    private static boolean checkHorizontal(List<String> input, int i, int j) {
        if (j + 3 < input.get(i).length()) {
            String line = input.get(i);
            int end = j + 4;
            return line.substring(j, end).equals("XMAS");
        }
        return false;
    }

    private static boolean checkHorizontalReverse(List<String> input, int i, int j) {
        if (j - 3 >= 0) {
            StringBuilder s = new StringBuilder(input.get(i).substring(j - 3, j + 1));
            String word = s.reverse().toString();
            return word.equals("XMAS");
        }
        return false;
    }

    private static boolean checkVertical(List<String> input, int i, int j) {
        if (i + 3 < input.size()) {
            char c0 = input.get(i).charAt(j);
            char c1 = input.get(i + 1).charAt(j);
            char c2 = input.get(i + 2).charAt(j);
            char c3 = input.get(i + 3).charAt(j);
            String word = "" + c0 + c1 + c2 + c3;
            return word.equals("XMAS");
        }
        return false;
    }

    private static boolean checkVerticalReverse(List<String> input, int i, int j) {
        if (i - 3 >= 0) {
            char c0 = input.get(i).charAt(j);
            char c1 = input.get(i - 1).charAt(j);
            char c2 = input.get(i - 2).charAt(j);
            char c3 = input.get(i - 3).charAt(j);
            String word = "" + c0 + c1 + c2 + c3;
            return word.equals("XMAS");
        }
        return false;
    }

    private static boolean checkLeftDiagonal(List<String> input, int i, int j) {
        if (i + 3 < input.size() && j - 3 >= 0) {
            char c0 = input.get(i).charAt(j);
            char c1 = input.get(i + 1).charAt(j - 1);
            char c2 = input.get(i + 2).charAt(j - 2);
            char c3 = input.get(i + 3).charAt(j - 3);
            String word = "" + c0 + c1 + c2 + c3;
            return word.equals("XMAS");
        }
        return false;
    }

    private static boolean checkRightDiagonal(List<String> input, int i, int j) {
        if (i + 3 < input.size() && j + 3 < input.get(i).length()) {
             char c0 = input.get(i).charAt(j);
             char c1 = input.get(i + 1).charAt(j + 1);
             char c2 = input.get(i + 2).charAt(j + 2);
             char c3 = input.get(i + 3).charAt(j + 3);
             String word = "" + c0 + c1 + c2 + c3;
             return word.equals("XMAS");
        }
        return false;
    }

    private static boolean checkLeftDiagonalReverse(List<String> input, int i, int j) {
        if (i - 3 >= 0 && j + 3 < input.get(i).length()) {
            char c0 = input.get(i).charAt(j);
            char c1 = input.get(i - 1).charAt(j + 1);
            char c2 = input.get(i - 2).charAt(j + 2);
            char c3 = input.get(i - 3).charAt(j + 3);
            String word = "" + c0 + c1 + c2 + c3;
            return word.equals("XMAS");
        }
        return false;
    }

    private static boolean checkRightDiagonalReverse(List<String> input, int i, int j) {
        if (i - 3 >= 0 && j - 3 >= 0) {
            char c0 = input.get(i).charAt(j);
            char c1 = input.get(i - 1).charAt(j - 1);
            char c2 = input.get(i - 2).charAt(j - 2);
            char c3 = input.get(i - 3).charAt(j - 3);
            String word = "" + c0 + c1 + c2 + c3;
            return word.equals("XMAS");
        }
        return false;
    }


}
