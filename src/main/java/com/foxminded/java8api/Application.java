package com.foxminded.java8api;


import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {

        Parser parser = new Parser();

        System.out.println(parser.parse("./src/main/index.txt"));



    }
}

