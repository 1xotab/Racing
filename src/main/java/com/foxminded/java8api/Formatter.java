package com.foxminded.java8api;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Formatter {

    public static final String NEW_LINE = "\n";
    public static final String VERTICAL_LINE = "|";
    public static final String DOT = ".";
    public static final String TABLE_SEPARATOR = "------------------------------------------";

    public String tableFormatter(Map<String, String> info) {

        int[] longestNameAndTeam = getLongestNameAndTeam(info.keySet(),);
        AtomicInteger counterOfRacers = new AtomicInteger(1);

        StringBuilder sb = new StringBuilder();

        info.forEach((naming, time) -> {

            if (counterOfRacers.get() == 16) {
                sb.append(TABLE_SEPARATOR + NEW_LINE);
            }

            String information = lineFormatter(naming, longestNameAndTeam, time);

            sb.append(counterOfRacers + DOT + information + NEW_LINE);
            counterOfRacers.getAndIncrement();
        });

        return sb.toString();
    }

    private int getLongestNameAndTeam(Set<String> lines,int zeroIfName_oneIfTeam) {

        Optional<Integer> length =  lines.stream().map(el -> {
            String name = el.split("_")[zeroIfName_oneIfTeam];
            return name.length();
        }).max(Integer::compare);

        return length.orElse(0);
    }

    private String lineFormatter(String nameAndTeam, int[] longestNamesAndTeams, String time) {
        int longestName = longestNamesAndTeams[0];
        int longestTeam = longestNamesAndTeams[1];
        String name = nameAndTeam.split("_")[0];
        String team = nameAndTeam.split("_")[1];

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

