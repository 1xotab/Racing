package com.foxminded.java8api;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class Parser {

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
            string=reader.readLine();
        }
        return result;
    }
}


