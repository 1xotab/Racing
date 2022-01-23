package com.foxminded.java8api;


import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;



public class Application {
    public static void main(String[] args) throws IOException, ParseException, DatatypeConfigurationException {

        Parser parser = new Parser();
//        Formatter formatter = new Formatter();
//
//        LinkedHashMap<String[],String> racersTime = parser.getRacersTime("./src/main/Index.txt","./src/main/anotherIndex.txt","./src/main/abbreviation.txt");
//
//        System.out.println(formatter.tableFormatter(racersTime));

        LinkedHashMap<ArrayList<String>,String> actual = parser.getRacersTime("./src/textDocuments/Index.txt",
                "./src/textDocuments/anotherIndex.txt","./src/textDocuments/abbreviation.txt");

        LinkedHashMap<ArrayList<String>,String> expected = new LinkedHashMap<>();

        expected.put(new ArrayList<> (Arrays.asList("Brendon Hartley", "SCUDERIA TORO ROSSO HONDA")),"0:00.001");
        expected.put(new ArrayList<> (Arrays.asList("Sebastian Vettel", "FERRARI")),"0:00.002");
        expected.put(new ArrayList<> (Arrays.asList("Daniel Ricciardo", "RED BULL RACING TAG HEUER")),"0:00.003");

        System.out.println(actual.equals(expected));


    }
}


