package dev.runje.ana.year2023.day05;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.LongStream;

import static dev.runje.ana.Helpers.getPath;

@Log
public class Day05 {

    public static void main(String[] args) {
        String[] directory = MethodHandles.lookup().lookupClass().getPackageName().split("\\.");
        String inputFilePath = getPath("input", directory);
//        String inputFilePath = getPath("example", directory);
        long result1 = 0;
        long result2 = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            List<String> input = br.lines().toList();
            String[] seeds = input.get(0).split(" ");
            long[] seedNumbers = Arrays.stream(Arrays.copyOfRange(seeds, 1, seeds.length)).mapToLong(Long::parseLong).toArray();
            List<Long> seedsExpanded = new ArrayList<>();
            List<Long> seedsStart;
            for (int i = 0; i < seedNumbers.length; i = i+2) {
                seedsStart = LongStream.range(seedNumbers[i], seedNumbers[i]+seedNumbers[i+1]).boxed().toList();
                for (Long aLong : seedsStart) {
                    if (!seedsExpanded.contains(aLong)) {
                        seedsExpanded.add(aLong);
                    }
                }
                System.out.println(seedsStart);
//                seedsExpanded.addAll(LongStream.range(seedNumbers[i], seedNumbers[i+1]+seedNumbers[i]).boxed().toList());
//                System.out.println(seedsExpanded);
            }
            long[] seedNumbersExpanded = new long[seedsExpanded.size()];
            for (int i = 0; i < seedsExpanded.size(); i++) {
                seedNumbersExpanded[i] = seedsExpanded.get(i);
            }
            System.out.println(Arrays.toString(seedNumbersExpanded));
//            long[][] finalMap1 = new long[2][seedNumbers.length-1];
            long[][] finalMap2 = new long[2][seedNumbersExpanded.length-1];
//            finalMap1[0] = seedNumbers;
            finalMap2[0] = seedNumbersExpanded;
            String soilToFertilizer = "soil-to-fertilizer map:";
            String fertilizerToWater = "fertilizer-to-water map:";
            String waterToLight = "water-to-light map:";
            String lightToTemperature = "light-to-temperature map:";
            String temperatureToHumidity = "temperature-to-humidity map:";
            String humidityToLocation = "humidity-to-location map:";

            List<String> fromToMap = new ArrayList<>();
            long[][] maps;
            int i = 3;

            while (!soilToFertilizer.equals(input.get(i))){
                if (!input.get(i).isEmpty())
                    fromToMap.add(input.get(i));
                i++;
            }
            i++;
            maps = new long[fromToMap.size()][3];
            for (int j = 0; j < fromToMap.size(); j++) {
                maps[j] = Arrays.stream(fromToMap.get(j).split(" ")).mapToLong(Long::parseLong).toArray();
            }
//            finalMap1[1] = mapping(finalMap1[0], maps);
            finalMap2[1] = mapping(finalMap2[0], maps);
            fromToMap.clear();

            while (!fertilizerToWater.equals(input.get(i))){
                if (!input.get(i).isEmpty())
                    fromToMap.add(input.get(i));
                i++;
            }
            i++;
            maps = new long[fromToMap.size()][3];
            for (int j = 0; j < fromToMap.size(); j++) {
                maps[j] = Arrays.stream(fromToMap.get(j).split(" ")).mapToLong(Long::parseLong).toArray();
            }
//            finalMap1[1] = mapping(finalMap1[1], maps);
            finalMap2[1] = mapping(finalMap2[1], maps);
            fromToMap.clear();

            while (!waterToLight.equals(input.get(i))){
                if (!input.get(i).isEmpty())
                    fromToMap.add(input.get(i));
                i++;
            }
            i++;
            maps = new long[fromToMap.size()][3];
            for (int j = 0; j < fromToMap.size(); j++) {
                maps[j] = Arrays.stream(fromToMap.get(j).split(" ")).mapToLong(Long::parseLong).toArray();
            }
//            finalMap1[1] = mapping(finalMap1[1], maps);
            finalMap2[1] = mapping(finalMap2[1], maps);
            fromToMap.clear();

            while (!lightToTemperature.equals(input.get(i))){
                if (!input.get(i).isEmpty())
                    fromToMap.add(input.get(i));
                i++;
            }
            i++;
            maps = new long[fromToMap.size()][3];
            for (int j = 0; j < fromToMap.size(); j++) {
                maps[j] = Arrays.stream(fromToMap.get(j).split(" ")).mapToLong(Long::parseLong).toArray();
            }
//            finalMap1[1] = mapping(finalMap1[1], maps);
            finalMap2[1] = mapping(finalMap2[1], maps);
            fromToMap.clear();

            while (!temperatureToHumidity.equals(input.get(i))){
                if (!input.get(i).isEmpty())
                    fromToMap.add(input.get(i));
                i++;
            }
            i++;
            maps = new long[fromToMap.size()][3];
            for (int j = 0; j < fromToMap.size(); j++) {
                maps[j] = Arrays.stream(fromToMap.get(j).split(" ")).mapToLong(Long::parseLong).toArray();
            }
//            finalMap1[1] = mapping(finalMap1[1], maps);
            finalMap2[1] = mapping(finalMap2[1], maps);
            fromToMap.clear();

            while (!humidityToLocation.equals(input.get(i))){
                if (!input.get(i).isEmpty())
                    fromToMap.add(input.get(i));
                i++;
            }
            i++;
            maps = new long[fromToMap.size()][3];
            for (int j = 0; j < fromToMap.size(); j++) {
                maps[j] = Arrays.stream(fromToMap.get(j).split(" ")).mapToLong(Long::parseLong).toArray();
            }
//            finalMap1[1] = mapping(finalMap1[1], maps);
            finalMap2[1] = mapping(finalMap2[1], maps);
            fromToMap.clear();

            while (i < input.size()){
                if (!input.get(i).isEmpty())
                    fromToMap.add(input.get(i));
                i++;
            }
            maps = new long[fromToMap.size()][3];
            for (int j = 0; j < fromToMap.size(); j++) {
                maps[j] = Arrays.stream(fromToMap.get(j).split(" ")).mapToLong(Long::parseLong).toArray();
            }
//            finalMap1[1] = mapping(finalMap1[1], maps);
            finalMap2[1] = mapping(finalMap2[1], maps);

//            result1 = Arrays.stream(finalMap1[1]).sorted().toArray()[0];
            result2 = Arrays.stream(finalMap2[1]).sorted().toArray()[0];

        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
        }
        System.out.printf("Result of part 1: %d\n", result1);
        System.out.printf("Result of part 2: %d\n", result2);

    }

    private static long[] mapping(long[] startArray, long[][] maps){
        long endNumber;
        long[] endArray = new long[startArray.length];
        for (int i = 0; i < startArray.length; i++){
            endNumber = 0;
            for (long[] map: maps){
                if (startArray[i] >= map[1] && startArray[i] < map[1] + map[2]){
                    endNumber = startArray[i]+map[0]-map[1];
                }
            }
            if (endNumber==0){
                endArray[i] = startArray[i];
            } else {
                endArray[i] = endNumber;
            }
        }
        return endArray;
    }
}
