package com.foxminded.java8api;


import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class Application {
    public static void main(String[] args) throws IOException, ParseException, DatatypeConfigurationException {

        NotParser parser = new NotParser();
        Formatter formatter = new Formatter();


       // System.out.println( parser.timeConverter(72013));

        Map<String,String> racersTime = parser.getRacersTime("./src/textDocuments/start (original).txt",
         "./src/textDocuments/end (original).txt","./src/textDocuments/abb (original).txt");

        System.out.println(formatter.tableFormatter(racersTime));

//        LinkedHashMap<ArrayList<String>,String> actual = parser.getRacersTime("./src/textDocuments/Index.txt",
//                "./src/textDocuments/anotherIndex.txt","./src/textDocuments/abbreviation.txt");
//
//        LinkedHashMap<ArrayList<String>,String> expected = new LinkedHashMap<>();
//
//        expected.put(new ArrayList<> (Arrays.asList("Brendon Hartley", "SCUDERIA TORO ROSSO HONDA")),"0:00.001");
//        expected.put(new ArrayList<> (Arrays.asList("Sebastian Vettel", "FERRARI")),"0:00.002");
//        expected.put(new ArrayList<> (Arrays.asList("Daniel Ricciardo", "RED BULL RACING TAG HEUER")),"0:00.003");
//
//        System.out.println(actual.equals(expected));


    }
}


