package com.foxminded.java8api;


import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.*;

public class Parser {


    public SortedMap<String, Long> getBestTime(String pathToStartLog, String pathToFinishLog) throws IOException {

        HashMap<String, LocalTime> startLog = parse(pathToStartLog);
        HashMap<String, LocalTime> finishLog = parse(pathToFinishLog);

        TreeMap<String, Long> bestLaps = new TreeMap<>();

        Set<String> teams = startLog.keySet();

        teams.forEach(team -> {

            LocalTime start = startLog.get(team);
            LocalTime finish = finishLog.get(team);

            long bestLap = MILLIS.between(start, finish);

            bestLaps.put(team, bestLap);

        });

        return bestLaps.descendingMap();
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

    public String[] abbreviationParser(String pathName, String abbreviation) throws IOException {

        File file = new File(pathName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String[] nameAndTeam = new String[2];

        String statement = reader.readLine();

        StringJoiner name = new StringJoiner(" ");
        StringJoiner team = new StringJoiner(" ");


        while (statement != null) {

            if (statement.contains(abbreviation)) {

                String[] abbreviationAndDecoding = statement.split("-");
                String[] decoding = abbreviationAndDecoding[1].split(" ");

                for (int i = 0; i < decoding.length; i++) {

                    if (i < 2) {
                        name.add(decoding[i]);
                    } else {
                        team.add(decoding[i]);
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

    public static String timeFormatter(long time) {

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

    public LinkedHashMap<String, String> mapFormatter(TreeMap<String, Long> bestLaps) {

        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        Set<String> keySet = bestLaps.keySet();

        keySet.stream().forEach(key -> result.put(key, timeFormatter(bestLaps.get(key))));

        return result;

    }

    public LinkedHashMap<String[], String> prepareInformationForLine(SortedMap<String, Long> bestLaps, String path) {

        LinkedHashMap<String[], String> result = new LinkedHashMap<>();

        bestLaps.entrySet().stream().forEach(key -> {

            try {
                String[] nameAndTeam = abbreviationParser(path, key.getKey());
                String time = timeFormatter(key.getValue());

                result.put(nameAndTeam, time);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return result;

    }

}



