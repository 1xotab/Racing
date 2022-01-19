package com.foxminded.java8api;


import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Timer;

import static java.time.temporal.ChronoUnit.*;


public class Application {
    public static void main(String[] args) throws IOException, ParseException, DatatypeConfigurationException {

//        Parser parser = new Parser();
//        System.out.println(parser.parse("./src/main/index.txt"));
//
//        parser.getBestTime("./src/main/index.txt","./src/main/anotherIndex.txt");

        LocalTime start = LocalTime.of(12, 45, 0,1000000);
        LocalTime finish = LocalTime.of(13, 0, 0,6000000);


        System.out.println(Parser.formatter(start,finish));


    }
}


