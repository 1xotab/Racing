package com.foxminded.java8api;


import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.*;

public class Parser {

    public LinkedHashMap<String[], String> getRacersTime(String pathToStartLog, String pathToFinishLog, String path) throws IOException {
        LinkedHashMap<String, Long> bestLaps = getBestTime(pathToStartLog, pathToFinishLog);
        LinkedHashMap<String[], String> result = new LinkedHashMap<>();

        bestLaps.entrySet().stream().forEach(key -> {
            try {
                String[] nameAndTeam = abbreviationParser(path, key.getKey());
                String time = timeConverter(key.getValue());

                result.put(nameAndTeam, time);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return result;

    }

    private LinkedHashMap<String, Long> getBestTime(String pathToStartLog, String pathToFinishLog) throws IOException {

        HashMap<String, LocalTime> startLog = timeDataParser(pathToStartLog);
        HashMap<String, LocalTime> finishLog = timeDataParser(pathToFinishLog);

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

    private HashMap<String, LocalTime> timeDataParser(String pathName) throws IOException {

        File file = new File(pathName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        HashMap<String, LocalTime> result = new HashMap<>();

        String string = reader.readLine();

        while (string != null) {

            ArrayList<Character> list = string.chars().mapToObj(e -> (char) e).collect(Collectors.toCollection(ArrayList::new));

            StringJoiner buffer = new StringJoiner("");
            list.stream().filter(Character::isLetter).forEach(el -> buffer.add(el.toString()));

            String name = buffer.toString();
            String dateAndTime = string.substring(name.length());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd_HH:mm:ss.SSS");

            LocalTime time = LocalTime.parse(dateAndTime, formatter);
            //LocalDate date = LocalDate.parse(dateAndTime,formatter);

            result.put(name, time);
            string = reader.readLine();
        }
        return result;
    }

    private String[] abbreviationParser(String pathName, String abbreviation) throws IOException {

        File file = new File(pathName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String[] nameAndTeam = new String[2];

        String statement = reader.readLine();

        StringJoiner name = new StringJoiner(" ");
        StringJoiner team = new StringJoiner(" ");

        while (statement != null) {

            if (statement.contains(abbreviation)) {

                 String decoding = statement.split("-")[1];
                 String[] splitDecoding = decoding.split(" ");

                for (int i = 0; i < splitDecoding.length; i++) {

                    if (i < 2) {
                        name.add(splitDecoding[i]);
                    } else {
                        team.add(splitDecoding[i]);
                    }
                }
                break;
            }
            statement = reader.readLine();
        }
        nameAndTeam[0] = name.toString();
        nameAndTeam[1] = team.toString();

        return nameAndTeam;
    }

    private String timeConverter(long time) {

        String ZERO = "0";

        long min = time / 1000 * 60;
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



