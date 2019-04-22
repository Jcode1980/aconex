package com.aconex.excavation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;


public class ApplicationTest {
    private static final String TEST_MAPS_FILE = "src/main/resources/test/TestMap.txt";
    private static final String TEST_INSTRUCTIONS_FILE="src/main/resources/test/TestInstructionsFile.txt";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;



    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @org.junit.Test
    public void main_shouldOutputCorrectValue(){
        Application.main(new String[]{TEST_MAPS_FILE, TEST_INSTRUCTIONS_FILE});
        System.err.print(outContent.toString());

        assertThat(outContent.toString(), containsString("Welcome to the Aconex site clearing simulator."));
        assertThat(outContent.toString(), containsString("The bulldozer is currently located at the Northern edge of the\n" +
                "site, immediately to the West of the site, and facing East."));
        assertThat(outContent.toString(), containsString("The simulation has ended at your request. These are the commands\n" +
                "you issued:"));
    }
}