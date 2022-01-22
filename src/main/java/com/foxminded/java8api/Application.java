package com.foxminded.java8api;


import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.IOException;
import java.text.ParseException;

import java.util.LinkedHashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.time.temporal.ChronoUnit.*;


public class Application {
    public static void main(String[] args) throws IOException, ParseException, DatatypeConfigurationException {

        Parser parser = new Parser();
        Formatter formatter = new Formatter();

        LinkedHashMap<String[],String> racersTime = parser.getRacersTime("./src/main/Index.txt","./src/main/anotherIndex.txt","./src/main/abbreviation.txt");

        System.out.println(formatter.tableFormatter(racersTime));

    }
}


