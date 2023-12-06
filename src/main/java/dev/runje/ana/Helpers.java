package dev.runje.ana;

public class Helpers {

    public static String getPath(String file, String[] directory){
        return  "/Users/ana.runje/training/advent-of-code/src/main/resources/"
                +directory[directory.length-2]
                +"/"+directory[directory.length-1]
                +"/"
                +file
                +".txt";
    }

    public static void printPartArray(char[][] chars, int startRow, int endRow, int startIndex, int endIndex) {
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startIndex; j <= endIndex; j++) {
                System.out.printf("%4c", chars[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
