package dev.runje.ana.year2015.day04;

import lombok.extern.java.Log;
import org.apache.commons.codec.digest.DigestUtils;

@Log
public class Day04 {

    public static void main(String[] args) {
        int result1 = 0;
        int result2 = 0;
        String secret = "yzbqklnj";
        int i = 1;
        int leadingZeroes;
        String extendedSecret;
        String md5Hex;
        while (true){
            extendedSecret = secret + i;
            md5Hex = DigestUtils.md5Hex(extendedSecret);
            leadingZeroes = ((int) md5Hex.chars().takeWhile(c -> c == '0').count());
            if (result1 == 0) {
                if (leadingZeroes == 5) result1 = i;
            } else if (result2 == 0 ) {
                if (leadingZeroes == 6) result2 = i;
            } else {
                break;
            }
            i++;
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }
}
