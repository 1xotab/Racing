package com.foxminded.java8api;


import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.*;

public class NotParser {

    public Map<String, String> getRacersTime(String pathToStartLog, String pathToFinishLog, String pathToAbbreviationDecoding) throws IOException {

        Map<String, Long> bestLaps = getBestTime(pathToStartLog, pathToFinishLog);
        LinkedHashMap<String, String> result = new LinkedHashMap<>();

        bestLaps.forEach((key1, value) -> {
            try {
                String nameAndTeam = abbreviationParser(pathToAbbreviationDecoding, key1);
                String time = timeConverter(value);

                result.put( nameAndTeam, time);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return result;

    }

    public Map<String, Long> getBestTime(String pathToStartLog, String pathToFinishLog) throws IOException {

        Map<String, LocalTime> startLog = timeDataParser(pathToStartLog);
        Map<String, LocalTime> finishLog = timeDataParser(pathToFinishLog);

        LinkedHashMap<String, Long> bestLaps = new LinkedHashMap<>();


        startLog.keySet().forEach(team -> {

            LocalTime start = startLog.get(team);
            LocalTime finish = finishLog.get(team);

            long bestLap = MILLIS.between(start, finish);

            bestLaps.put(team, bestLap);

        });

        bestLaps.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(el -> {

                    bestLaps.remove(el.getKey());
                    bestLaps.put(el.getKey(), el.getValue());

                });

        return bestLaps;
    }

    public Map<String, LocalTime> timeDataParser(String pathName) throws IOException {

        File file = new File(pathName);
        HashMap<String, LocalTime> result = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String string = reader.readLine();

            while (string != null) {

                ArrayList<Character> list = string.chars().mapToObj(e -> (char) e).collect(Collectors.toCollection(ArrayList::new));

                StringJoiner buffer = new StringJoiner("");

                list.stream().filter(Character::isLetter).forEach(el -> buffer.add(el.toString()));

                String name = buffer.toString();
                String dateAndTime = string.substring(name.length());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd_HH:mm:ss.SSS");
                LocalTime time = LocalTime.parse(dateAndTime, formatter);

                result.put(name, time);
                string = reader.readLine();
            }
        }
        return result;
    }

    public String abbreviationParser(String pathName, String abbreviation) throws IOException {

        File file = new File(pathName);
        String nameAndTeam = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String statement = reader.readLine();

            while (statement != null) {

                if (statement.contains(abbreviation)) {

                    String[] abbreviationDecoding = statement.split("_");

                    nameAndTeam = statement.substring(abbreviationDecoding[0].length());

                    break;
                }
                statement = reader.readLine();
            }
        }
        return nameAndTeam;
    }

    public String timeConverter(long time) {

        String ZERO = "0";

        long min = time / (1000 * 60);
        long sec = (time - min * 60 * 1000) / 1000;
        long milliSec = (time - min * 60 * 1000 - sec * 1000);

        String secFormat = "";
        if (sec < 10) {
            secFormat = ZERO;
        }

        String milliSecFormat = "";
        if (milliSec < 100) {
            milliSecFormat = ZERO;

            if (milliSec < 10) {
                milliSecFormat = ZERO + ZERO;
            }
        }
        return min + ":" + secFormat + sec + "." + milliSecFormat + milliSec;
    }
}

