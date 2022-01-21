package com.foxminded.java8api;


import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.IOException;
import java.text.ParseException;

import java.util.TreeMap;

import static java.time.temporal.ChronoUnit.*;


public class Application {
    public static void main(String[] args) throws IOException, ParseException, DatatypeConfigurationException {

        Parser parser = new Parser();

       // TreeMap<String,Long> map =  parser.getBestTime("./src/main/index.txt","./src/main/anotherIndex.txt");


        String[] array = parser.abbreviationParser("./src/main/abbreviation.txt","SVF");

        System.out.println(array[0]);
        System.out.println(array[1]);



    }
}


