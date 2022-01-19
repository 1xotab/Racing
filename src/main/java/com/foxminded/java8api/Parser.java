package com.foxminded.java8api;


import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.*;

public class Parser {

    public HashMap<String, String> getBestTime(String pathToStartLog, String pathToFinishLog) throws IOException {

        HashMap<String, LocalTime> startLog = parse(pathToStartLog);
        HashMap<String, LocalTime> finishLog = parse(pathToFinishLog);

        HashMap<String, String> bestLaps = new HashMap<>();

        Set<String> teams = startLog.keySet();
        String result;

        teams.stream().forEach(team -> {

            LocalTime start = startLog.get(team);
            LocalTime finish = finishLog.get(team);
            String bestTime = formatter(start, finish);
            bestLaps.put(team, bestTime);

        });

        return bestLaps;
    }

    public HashMap<String, LocalTime> parse(String pathName) throws IOException {

        File file = new File(pathName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        HashMap<String, LocalTime> result = new HashMap<>();

        String string = reader.readLine();

        while (string != null) {


            char[] charsArray = string.toCharArray();
            List<Character> list = new ArrayList<>();
            for (Character ch : charsArray) {
                list.add(ch);
            }

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

    public static String formatter(LocalTime start, LocalTime finish) {

        String ZERO = "0";

        long min = MINUTES.between(start, finish);
        long sec = SECONDS.between(start, finish) - min * 60;
        long milliSec = MILLIS.between(start, finish) - min * 60 * 1000 - sec * 1000;

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


