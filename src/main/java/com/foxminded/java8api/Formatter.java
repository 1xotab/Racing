package com.foxminded.java8api;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Formatter {

    public static final String NEW_LINE = "\n";
    public static final String VERTICAL_LINE = "|";
    public static final String DOT = ".";
    public static final String TABLE_SEPARATOR = "------------------------------------------";

    public String tableFormatter(LinkedHashMap<String[], String> info) {

        int[] longestNameAndTeam = getLongestNameAndTeam(info.keySet());
        AtomicInteger counterOfRacers = new AtomicInteger(1);

        StringBuilder sb = new StringBuilder();

        info.forEach((naming, time) -> {

            if(counterOfRacers.get()==16){
                sb.append(TABLE_SEPARATOR + NEW_LINE);
            }

            String information = lineFormatter(naming, longestNameAndTeam, time);

            sb.append(counterOfRacers + DOT + information + NEW_LINE);
            counterOfRacers.getAndIncrement();
        });

        return sb.toString();
    }

    private int[] getLongestNameAndTeam(Set<String[]> info) {
        AtomicInteger maxName = new AtomicInteger(1);
        AtomicInteger maxTeam = new AtomicInteger(1);

        info.forEach(array -> {

            if (maxName.get() < array[0].length()) {
                maxName.set(array[0].length());
            }
            if (maxTeam.get() < array[1].length()) {
                maxTeam.set(array[1].length());
            }
        });

        int[] maxNameAndTeam = new int[2];
        maxNameAndTeam[0] = maxName.intValue();
        maxNameAndTeam[1] = maxTeam.intValue();

        return maxNameAndTeam;
    }

    private String lineFormatter(String[] nameAndTeam, int[] longestNamesAndTeams, String time) {
        int longestName = longestNamesAndTeams[0];
        int longestTeam = longestNamesAndTeams[1];
        String name = nameAndTeam[0];
        String team = nameAndTeam[1];

        String nameIndent = spaceGen(longestName - name.length());
        String teamIndent = spaceGen(longestTeam - team.length());

        return name + nameIndent + VERTICAL_LINE + team + teamIndent + VERTICAL_LINE + time;


    }

    private String spaceGen(int quantity) {

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < quantity) {
            sb.append(" ");
            i++;
        }
        return sb.toString();
    }

}

