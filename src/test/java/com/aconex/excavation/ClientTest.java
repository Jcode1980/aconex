package com.aconex.excavation;

import com.aconex.excavation.enums.ActionType;
import com.aconex.excavation.model.job.IExcavationJob;
import com.aconex.excavation.model.job.IExcavator;
import com.aconex.excavation.model.job.Instruction;
import com.aconex.excavation.service.job.ExcavationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {
    private static final String TEST_MAPS_FILE = "src/main/resources/test/TestMap.txt";
    private static final String TEST_INSTRUCTIONS_FILE="src/main/resources/test/TestInstructionsFile.txt";
    private static final String TEST_INSTRUCTIONS_OFB_FILE="src/main/resources/test/TestInstructionsOutOfBounds.txt";

    @Mock
    private ExcavationService excavationService;

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

    @Test
    public void startExcavationSimluator() throws IOException {
        Path testInstructionsPath = Paths.get(TEST_INSTRUCTIONS_FILE);
        Client client = new Client(TEST_MAPS_FILE, new FileInputStream(testInstructionsPath.toFile()));
        client.startExcavationSimluator();
        assertThat(outContent.toString(), containsString("Welcome to the Aconex site clearing simulator. This is a map of"));
    }

    @Test
    public void IT_processCommandFile_shouldOutputExcavatorFuelUsage() throws IOException {
        Path testInstructionsPath = Paths.get(TEST_INSTRUCTIONS_FILE);
        Client client = new Client(TEST_MAPS_FILE, new FileInputStream(testInstructionsPath.toFile()));
        client.startExcavationSimluator();
        IExcavationJob job = client.job();
        assertThat(job.excavator().fuelUsed(), is(19));

    }


    @Test
    public void IT_processCommandFile_shouldHitBoundaryNotStopSimulation() throws IOException {
        Path testInstructionsPath = Paths.get(TEST_INSTRUCTIONS_OFB_FILE);
        Client client = new Client(TEST_MAPS_FILE, new FileInputStream(testInstructionsPath.toFile()));
        client.startExcavationSimluator();
        IExcavationJob job = client.job();
        assertThat(outContent.toString(), containsString("******* Error. Exavator is trying to move to an invalid spot. Simulation Terminated********"));
    }

}