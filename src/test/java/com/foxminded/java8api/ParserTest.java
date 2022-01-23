package com.foxminded.java8api;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    Parser parser = new Parser();

    @Test
    void getRacersTime_shouldReturnSortedTable_whenInputFilesAreCorrected() throws IOException {

        LinkedHashMap<ArrayList<String>,String> actual = parser.getRacersTime("./src/textDocuments/Index.txt",
                "./src/textDocuments/anotherIndex.txt","./src/textDocuments/abbreviation.txt");

        LinkedHashMap<ArrayList<String>,String> expected = new LinkedHashMap<>();

        expected.put(new ArrayList<> (Arrays.asList("Brendon Hartley", "SCUDERIA TORO ROSSO HONDA")),"0:00.001");
        expected.put(new ArrayList<> (Arrays.asList("Sebastian Vettel", "FERRARI")),"0:00.002");
        expected.put(new ArrayList<> (Arrays.asList("Daniel Ricciardo", "RED BULL RACING TAG HEUER")),"0:00.003");

       assertEquals(actual,expected);

    }


}
